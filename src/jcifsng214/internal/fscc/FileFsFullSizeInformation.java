/*
 * © 2017 AgNO3 Gmbh & Co. KG
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package jcifsng214.internal.fscc;


import jcifsng214.Decodable;
import jcifsng214.internal.AllocInfo;
import jcifsng214.internal.SMBProtocolDecodingException;
import jcifsng214.internal.util.SMBUtil;


/**
 *
 */
public class FileFsFullSizeInformation implements AllocInfo, FileSystemInformation, Decodable {

    private long alloc; // Also handles SmbQueryFSSizeInfo
    private long free;
    private int sectPerAlloc;
    private int bytesPerSect;


    /**
     * {@inheritDoc}
     *
     * @see jcifsng214.internal.fscc.FileSystemInformation#getFileSystemInformationClass()
     */
    @Override
    public byte getFileSystemInformationClass () {
        return FS_FULL_SIZE_INFO;
    }


    @Override
    public long getCapacity () {
        return this.alloc * this.sectPerAlloc * this.bytesPerSect;
    }


    @Override
    public long getFree () {
        return this.free * this.sectPerAlloc * this.bytesPerSect;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng214.Decodable#decode(byte[], int, int)
     */
    @Override
    public int decode ( byte[] buffer, int bufferIndex, int len ) throws SMBProtocolDecodingException {
        int start = bufferIndex;

        // Read total allocation units.
        this.alloc = SMBUtil.readInt8(buffer, bufferIndex);
        bufferIndex += 8;

        // read caller available allocation units
        this.free = SMBUtil.readInt8(buffer, bufferIndex);
        bufferIndex += 8;

        // skip actual free units
        bufferIndex += 8;

        this.sectPerAlloc = SMBUtil.readInt4(buffer, bufferIndex);
        bufferIndex += 4;

        this.bytesPerSect = SMBUtil.readInt4(buffer, bufferIndex);
        bufferIndex += 4;

        return bufferIndex - start;
    }


    @Override
    public String toString () {
        return new String(
            "SmbInfoAllocation[" + "alloc=" + this.alloc + ",free=" + this.free + ",sectPerAlloc=" + this.sectPerAlloc + ",bytesPerSect="
                    + this.bytesPerSect + "]");
    }

}