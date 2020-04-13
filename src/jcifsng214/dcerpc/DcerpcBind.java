/* jcifs msrpc client library in Java
 * Copyright (C) 2006  "Michael B. Allen" <jcifs at samba dot org>
 *                     "Eric Glass" <jcifs at samba dot org>
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

package jcifsng214.dcerpc;


import jcifsng214.dcerpc.ndr.NdrBuffer;
import jcifsng214.dcerpc.ndr.NdrException;


/**
 * 
 * @author mbechler
 *
 */
public class DcerpcBind extends DcerpcMessage {

    private static final String[] result_message = {
        "0", "DCERPC_BIND_ERR_ABSTRACT_SYNTAX_NOT_SUPPORTED", "DCERPC_BIND_ERR_PROPOSED_TRANSFER_SYNTAXES_NOT_SUPPORTED",
        "DCERPC_BIND_ERR_LOCAL_LIMIT_EXCEEDED"
    };


    private static String getResultMessage ( int result ) {
        return result < 4 ? result_message[ result ] : "0x" + jcifsng214.util.Hexdump.toHexString(result, 4);
    }


    @Override
    public DcerpcException getResult () {
        if ( this.result != 0 )
            return new DcerpcException(getResultMessage(this.result));
        return null;
    }

    private DcerpcBinding binding;
    private int max_xmit, max_recv;


    /**
     * Construct bind message
     * 
     */
    public DcerpcBind () {}


    DcerpcBind ( DcerpcBinding binding, DcerpcHandle handle ) {
        this.binding = binding;
        this.max_xmit = handle.getMaxXmit();
        this.max_recv = handle.getMaxRecv();
        this.ptype = 11;
        this.flags = DCERPC_FIRST_FRAG | DCERPC_LAST_FRAG;
    }


    @Override
    public int getOpnum () {
        return 0;
    }


    @Override
    public void encode_in ( NdrBuffer buf ) throws NdrException {
        buf.enc_ndr_short(this.max_xmit);
        buf.enc_ndr_short(this.max_recv);
        buf.enc_ndr_long(0); /* assoc. group */
        buf.enc_ndr_small(1); /* num context items */
        buf.enc_ndr_small(0); /* reserved */
        buf.enc_ndr_short(0); /* reserved2 */
        buf.enc_ndr_short(0); /* context id */
        buf.enc_ndr_small(1); /* number of items */
        buf.enc_ndr_small(0); /* reserved */
        this.binding.getUuid().encode(buf);
        buf.enc_ndr_short(this.binding.getMajor());
        buf.enc_ndr_short(this.binding.getMinor());
        DCERPC_UUID_SYNTAX_NDR.encode(buf);
        buf.enc_ndr_long(2); /* syntax version */
    }


    @Override
    public void decode_out ( NdrBuffer buf ) throws NdrException {
        buf.dec_ndr_short(); /* max transmit frag size */
        buf.dec_ndr_short(); /* max receive frag size */
        buf.dec_ndr_long(); /* assoc. group */
        int n = buf.dec_ndr_short(); /* secondary addr len */
        buf.advance(n); /* secondary addr */
        buf.align(4);
        buf.dec_ndr_small(); /* num results */
        buf.align(4);
        this.result = buf.dec_ndr_short();
        buf.dec_ndr_short();
        buf.advance(20); /* transfer syntax / version */
    }
}
