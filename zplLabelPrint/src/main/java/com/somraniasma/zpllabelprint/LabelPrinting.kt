package com.somraniasma.zpllabelprint

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import java.lang.ref.WeakReference

class LabelPrinting(
    private val contet: Activity,
    private val vpn: Boolean,
    private val ipAdress: String,
    private val port: Int, private val msg: String
) :
    AsyncTask<String, Void, String>() {
    var pd: ProgressDialog? = null
    var resultPrint = false
    var result = "0"

    private var printWeakReference: WeakReference<Activity>? = null


    fun LabelPrinting(context: Activity) {
        printWeakReference = WeakReference(contet)
    }

    override fun onPreExecute() {
        super.onPreExecute()
        result = "0"
        pd = ProgressDialog(contet)
        pd!!.setMessage("Loading...")
        pd!!.show()
    }

    override fun doInBackground(vararg p0: String?): String? {
        var context = printWeakReference?.get()
        Log.e(
            "_______ ",
            " " + ipAdress + " __" + port + "____" + msg
        )
        Log.e("VPN..", " ")

        try {
            var networkState = InternetUtils.networkCheck(contet)
            if (ipAdress == null || ipAdress.equals("") || port.equals("") || port == null) {

                result = "3"

            } else if (networkState.contains("0")) {
                Log.e("TEST..", "0 ")
                result = "2"

            } else if (vpn) {

                if (networkState.contains("1")) {
                    Log.e("TEST..", "1 ")
                    result = "1"
                }

            } else if (networkState.contains("2")) {
                Log.e("TEST..", "2 ")
                result = "0"

                SocketConnecting.startPrinting(
                    msg,
                    ipAdress,//"192.168.1.149",
                    port//9100
                )
                resultPrint = true

                Log.e("ZPL..", " $msg")
                Log.e("IP ADRESS..", " $ipAdress")
                Log.e("PORT..", " $port")
            }

        } catch (e: Exception) {
            Log.e(
                "EXCEPTIONPRINT ",
                "IOException $e" + " " + e.printStackTrace().toString() + " " + e.message
            )
            resultPrint = false

        }
        return result
    }

    override fun onPostExecute(result: String?) {

        pd?.dismiss()

        if (result != null && result.contains("2")) {
            AlertDialog.Builder(contet)
                .setMessage("SVP vérifiez votre connexion Internet!")
                .setCancelable(false)
                .setTitle("Connexion Internet")
                .setNegativeButton("Ok") { dialog, id -> }
                .show()
        } else if (result != null && result.contains("1")) {
            AlertDialog.Builder(contet)
                .setMessage("SVP vérifiez votre connexion VPN!")
                .setCancelable(false)
                .setTitle("Connexion VPN")
                .setNegativeButton("Ok") { dialog, id -> }
                .show()
        } else if (result != null && result.contains("3")) {
            AlertDialog.Builder(contet)
                .setMessage("Merci de vaider la configuration imprimante au niveau de l'interface Setting")
                .setCancelable(false)
                .setTitle("Parametres imprimante")
                .setNegativeButton("Ok") { dialog, id -> }
                .show()
        }

        if (resultPrint) {
            Toast.makeText(contet, "etiquette imprimé", Toast.LENGTH_SHORT)
                .show()
            //  prod.setPrintFlag("Y");
            //  productRepository.updtePrintState(prod);
        } else {
            Toast.makeText(
                contet,
                "Impression echouée !",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}