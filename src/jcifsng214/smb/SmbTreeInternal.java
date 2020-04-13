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
package jcifsng214.smb;


import jcifsng214.CIFSContext;
import jcifsng214.CIFSException;
import jcifsng214.SmbTree;
import jcifsng214.internal.CommonServerMessageBlockResponse;
import jcifsng214.internal.Request;


/**
 * @author mbechler
 * @internal
 */
public interface SmbTreeInternal extends SmbTree {

    /**
     * @param tf
     * @throws SmbException
     */
    void connectLogon ( CIFSContext tf ) throws SmbException;


    /**
     * @param request
     * @param params
     * @return response message
     * @throws CIFSException
     */
    <T extends CommonServerMessageBlockResponse> T send ( Request<T> request, RequestParam... params ) throws CIFSException;
}
