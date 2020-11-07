package com.somraniasma.zpllabelprint

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket
import java.net.UnknownHostException

class SocketConnecting{
    companion object {


        @Throws(UnknownHostException::class, IOException::class)
        fun startPrinting(
            message: String?,
            adresse: String?,
            port: Int
        ) {
            val socket = Socket(adresse, port)

            // Socket clientSocket = ServerSocket.accept()
            val dOut = DataOutputStream(socket.getOutputStream())
            if (socket.isClosed) {
                dOut.close()
            } else {
                dOut.writeByte(1)
                dOut.writeUTF(message)
                dOut.flush()
                dOut.writeByte(-1)
                dOut.flush()
                dOut.close()
            }
        }




    }


}