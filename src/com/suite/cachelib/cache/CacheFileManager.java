package com.suite.cachelib.cache;

import cacheLib.cache.CacheFile;
import cacheLib.cache.ContainersInformation;
import cacheLib.io.Stream;
import com.suite.cachelib.util.Utils;

public class CacheFileManager {
	private Cache cache;
	private CacheFile cacheFile;
	private ContainersInformation information;
	private boolean discardFilesData;
	private byte[][][] filesData;

	public CacheFileManager(Cache cache, CacheFile cacheFile, boolean discardFilesData) {
		this.cache = cache;
		this.cacheFile = cacheFile;
		this.discardFilesData = discardFilesData;
		byte[] informContainerPackedData = cache.getConstainersInformCacheFile().getContainerData(cacheFile.getIndexFileId());
		if (informContainerPackedData == null) {
			cache.getCacheFileManagers()[cacheFile.getIndexFileId()] = null;
		} else {
			this.information = new ContainersInformation(informContainerPackedData);
			this.resetFilesData();
		}
	}

	public CacheFile getCacheFile() {
		return this.cacheFile;
	}

	public int getContainersSize() {
		return this.information.getContainers().length;
	}

	public int getFilesSize(int containerId) {
		return !this.validContainer(containerId) ? -1 : this.information.getContainers()[containerId].getFiles().length;
	}

	public void resetFilesData() {
		this.filesData = new byte[this.information.getContainers().length][][];
	}

	public boolean validFile(int containerId, int fileId) {
		if (!this.validContainer(containerId)) {
			return false;
		} else {
			return fileId >= 0 && this.information.getContainers()[containerId].getFiles().length > fileId;
		}
	}

	public boolean validContainer(int containerId) {
		return containerId >= 0 && this.information.getContainers().length > containerId;
	}

	public byte[] getFileData(int containerId, int fileId) {
		return this.getFileData(containerId, fileId, (int[])null);
	}

	public boolean putAllContainers(Cache sourceCache) {
		if (sourceCache.getCacheFileManagers().length > this.cacheFile.getIndexFileId() && sourceCache.getCacheFileManagers()[this.cacheFile.getIndexFileId()] != null) {
			for(int containerIndex = 0; containerIndex < sourceCache.getCacheFileManagers()[this.cacheFile.getIndexFileId()].getInformation().getContainersIndexes().length; ++containerIndex) {
				if (!this.putContainer(sourceCache.getCacheFileManagers()[this.cacheFile.getIndexFileId()].getInformation().getContainersIndexes()[containerIndex], sourceCache)) {
					return false;
				}
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean putContainer(int containerId, Cache sourceCache) {
		if (sourceCache.getCacheFileManagers().length > this.cacheFile.getIndexFileId() && sourceCache.getCacheFileManagers()[this.cacheFile.getIndexFileId()] != null) {
			byte[] packedContainer = sourceCache.getCacheFileManagers()[this.cacheFile.getIndexFileId()].getCacheFile().getContainerData(containerId);
			if (packedContainer == null) {
				return false;
			} else {
				this.information.addContainer(containerId);
				this.information.getContainers()[containerId].setFiles(Utils.copyArray(sourceCache.getCacheFileManagers()[this.cacheFile.getIndexFileId()].getInformation().getContainers()[containerId].getFiles()));
				this.information.getContainers()[containerId].setFilesIndexes(Utils.copyArray(sourceCache.getCacheFileManagers()[this.cacheFile.getIndexFileId()].getInformation().getContainers()[containerId].getFilesIndexes()));
				this.information.getContainers()[containerId].setNameHash(sourceCache.getCacheFileManagers()[this.cacheFile.getIndexFileId()].getInformation().getContainers()[containerId].getNameHash());
				if (!this.cacheFile.putContainerData(containerId, packedContainer)) {
					return false;
				} else {
					Utils.CRC32Instance.reset();
					Utils.CRC32Instance.update(packedContainer, 0, packedContainer.length - 2);
					this.information.getContainers()[containerId].setCrc((int)Utils.CRC32Instance.getValue());
					if (!this.information.getContainers()[containerId].isUpdated()) {
						this.information.getContainers()[containerId].updateVersion();
					}

					if (!this.information.isUpdated()) {
						this.information.updateRevision();
					}

					if (!this.information.getInformationContainer().isUpdated()) {
						this.information.getInformationContainer().updateVersion();
					}

					byte[] informationContainer = this.information.encodeContainersInformation();
					if (this.cache.getConstainersInformCacheFile().putContainerUnpackedData(this.cacheFile.getIndexFileId(), 2, informationContainer, this.information.getInformationContainer().getVersion())) {
						Utils.CRC32Instance.reset();
						Utils.CRC32Instance.update(informationContainer, 0, informationContainer.length);
						this.information.getInformationContainer().setCrc((int)Utils.CRC32Instance.getValue());
						this.resetFilesData();
						return true;
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
	}

	public boolean putFileData(int containerId, int fileId, byte[] data) {
		return this.putFileData(containerId, fileId, 2, data);
	}

	public boolean putFileData(int containerId, int fileId, int compression, byte[] data) {
		return this.putFileData(containerId, fileId, compression, data, (int[])null);
	}

	public boolean putFileData(int containerId, int fileId, int compression, byte[] data, int[] container_keys) {
		return this.putFileData(containerId, fileId, compression, data, container_keys, (String)null, (String)null);
	}

	public boolean putFileData(int containerId, int fileId, int compression, byte[] data, int[] container_keys, String containerName, String fileName) {
		int oldFileIndexesLength = 0;
		if (this.validContainer(containerId) && this.information.getContainers()[containerId].getFilesIndexes() != null) {
			oldFileIndexesLength = this.information.getContainers()[containerId].getFilesIndexes().length;
		}

		int containerIndex = this.information.addContainer(containerId);
		if (containerIndex == -1) {
			return false;
		} else {
			int fileIndex = this.information.addFile(containerId, fileId);
			if (fileIndex == -1) {
				return false;
			} else {
				byte[] containerData;
				byte[] informationContainer;
				if (this.information.getContainers()[containerId].getFilesIndexes().length == 1) {
					containerData = data;
				} else {
					int[] filesSize = new int[this.information.getContainers()[containerId].getFilesIndexes().length];
					byte[][] filesBufferData = new byte[this.information.getContainers()[containerId].getFilesIndexes().length][];
					int readPosition;
					int amtOfLoops;
					if (oldFileIndexesLength > 0) {
						informationContainer = this.cacheFile.getContainerUnpackedData(containerId, container_keys);
						if (oldFileIndexesLength == 1) {
							filesSize[0] = informationContainer.length;
							filesBufferData[0] = informationContainer;
						} else {
							readPosition = informationContainer.length;
							--readPosition;
							amtOfLoops = informationContainer[readPosition] & 255;
							readPosition -= amtOfLoops * oldFileIndexesLength * 4;
							Stream stream = new Stream(informationContainer);
							stream.offset = readPosition;
							int sourceOffset = 0;

							label129:
							while(true) {
								int loop;
								int dataRead;
								if (sourceOffset >= amtOfLoops) {
									for(sourceOffset = 0; sourceOffset < oldFileIndexesLength; ++sourceOffset) {
										filesBufferData[sourceOffset] = new byte[filesSize[sourceOffset]];
										filesSize[sourceOffset] = 0;
									}

									stream.offset = readPosition;
									sourceOffset = 0;
									loop = 0;

									while(true) {
										if (loop >= amtOfLoops) {
											break label129;
										}

										dataRead = 0;

										for(int thisFileIndex = 0; thisFileIndex < oldFileIndexesLength; ++thisFileIndex) {
											dataRead += stream.getInt();
											System.arraycopy(informationContainer, sourceOffset, filesBufferData[thisFileIndex], filesSize[thisFileIndex], dataRead);
											sourceOffset += dataRead;
											filesSize[thisFileIndex] += dataRead;
										}

										++loop;
									}
								}

								loop = 0;

								for(dataRead = 0; dataRead < oldFileIndexesLength; ++dataRead) {
									filesSize[dataRead] += loop += stream.getInt();
								}

								++sourceOffset;
							}
						}
					}

					filesSize[fileIndex] = data.length;
					filesBufferData[fileIndex] = data;
					Stream stream = new Stream(1000000);

					for(readPosition = 0; readPosition < this.information.getContainers()[containerId].getFilesIndexes().length; ++readPosition) {
						for(amtOfLoops = 0; amtOfLoops < filesBufferData[readPosition].length; ++amtOfLoops) {
							stream.putByte(filesBufferData[readPosition][amtOfLoops]);
						}
					}

					for(readPosition = 0; readPosition < this.information.getContainers()[containerId].getFilesIndexes().length; ++readPosition) {
						stream.putInt(filesSize[readPosition] - (readPosition != 0 ? filesSize[readPosition - 1] : 0));
					}

					stream.putByte(1);
					containerData = new byte[stream.offset];
					stream.offset = 0;
					stream.getBytes(containerData, 0, containerData.length);
				}

				int version = this.information.getContainers()[containerId].getNextVersion();
				if (!this.cacheFile.putContainerUnpackedData(containerId, compression, containerData, version, container_keys)) {
					return false;
				} else {
					byte[] packedContainer = this.cacheFile.getContainerData(containerId);
					if (packedContainer == null) {
						return false;
					} else {
						Utils.CRC32Instance.reset();
						Utils.CRC32Instance.update(packedContainer, 0, packedContainer.length - 2);
						this.information.getContainers()[containerId].setCrc((int)Utils.CRC32Instance.getValue());
						if (containerName != null) {
							this.information.getContainers()[containerId].setNameHash(Utils.getNameHash(containerName));
						}

						if (fileName != null) {
							this.information.getContainers()[containerId].getFiles()[fileId].setNameHash(Utils.getNameHash(fileName));
						}

						if (!this.information.getContainers()[containerId].isUpdated()) {
							this.information.getContainers()[containerId].updateVersion();
						}

						if (!this.information.isUpdated()) {
							this.information.updateRevision();
						}

						if (!this.information.getInformationContainer().isUpdated()) {
							this.information.getInformationContainer().updateVersion();
						}

						informationContainer = this.information.encodeContainersInformation();
						if (this.cache.getConstainersInformCacheFile().putContainerUnpackedData(this.cacheFile.getIndexFileId(), 2, informationContainer, this.information.getInformationContainer().getVersion())) {
							Utils.CRC32Instance.reset();
							Utils.CRC32Instance.update(informationContainer, 0, informationContainer.length);
							this.information.getInformationContainer().setCrc((int)Utils.CRC32Instance.getValue());
							this.resetFilesData();
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
	}

	public boolean loadFilesData(int containerId, int[] container_keys) {
		byte[] data = this.cacheFile.getContainerUnpackedData(containerId, container_keys);
		if (data == null) {
			return false;
		} else {
			if (this.filesData[containerId] == null) {
				if (this.information.getContainers()[containerId] == null) {
					return false;
				}

				this.filesData[containerId] = new byte[this.information.getContainers()[containerId].getFiles().length][];
			}

			int readPosition;
			if (this.information.getContainers()[containerId].getFilesIndexes().length == 1) {
				readPosition = this.information.getContainers()[containerId].getFilesIndexes()[0];
				this.filesData[containerId][readPosition] = data;
			} else {
				readPosition = data.length;
				--readPosition;
				int amtOfLoops = data[readPosition] & 255;
				readPosition -= amtOfLoops * this.information.getContainers()[containerId].getFilesIndexes().length * 4;
				Stream stream = new Stream(data);
				int[] filesSize = new int[this.information.getContainers()[containerId].getFilesIndexes().length];
				stream.offset = readPosition;

				int sourceOffset;
				int fileIndex;
				for(int loop = 0; loop < amtOfLoops; ++loop) {
					sourceOffset = 0;

					for(fileIndex = 0; fileIndex < this.information.getContainers()[containerId].getFilesIndexes().length; ++fileIndex) {
						filesSize[fileIndex] += sourceOffset += stream.getInt();
					}
				}

				byte[][] filesBufferData = new byte[this.information.getContainers()[containerId].getFilesIndexes().length][];

				for(sourceOffset = 0; sourceOffset < this.information.getContainers()[containerId].getFilesIndexes().length; ++sourceOffset) {
					filesBufferData[sourceOffset] = new byte[filesSize[sourceOffset]];
					filesSize[sourceOffset] = 0;
				}

				stream.offset = readPosition;
				sourceOffset = 0;

				for(fileIndex = 0; fileIndex < amtOfLoops; ++fileIndex) {
					int dataRead = 0;

					for(fileIndex = 0; fileIndex < this.information.getContainers()[containerId].getFilesIndexes().length; ++fileIndex) {
						dataRead += stream.getInt();
						System.arraycopy(data, sourceOffset, filesBufferData[fileIndex], filesSize[fileIndex], dataRead);
						sourceOffset += dataRead;
						filesSize[fileIndex] += dataRead;
					}
				}

				for(fileIndex = 0; fileIndex < this.information.getContainers()[containerId].getFilesIndexes().length; ++fileIndex) {
					this.filesData[containerId][this.information.getContainers()[containerId].getFilesIndexes()[fileIndex]] = filesBufferData[fileIndex];
				}
			}

			return true;
		}
	}

	public byte[] getFileData(int containerId, int fileId, int[] container_keys) {
		if (!this.validFile(containerId, fileId)) {
			return null;
		} else if ((this.filesData[containerId] == null || this.filesData[containerId][fileId] == null) && !this.loadFilesData(containerId, container_keys)) {
			return null;
		} else {
			byte[] data = this.filesData[containerId][fileId];
			if (this.discardFilesData) {
				if (this.filesData[containerId].length == 1) {
					this.filesData[containerId] = (byte[][])null;
				} else {
					this.filesData[containerId][fileId] = null;
				}
			}

			return data;
		}
	}

	public ContainersInformation getInformation() {
		return this.information;
	}
}
