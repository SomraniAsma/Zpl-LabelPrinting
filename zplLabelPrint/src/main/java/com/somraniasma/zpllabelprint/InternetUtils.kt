package com.somraniasma.zpllabelprint

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log


class InternetUtils {
    companion object {
        private var internet = false
        private var result = "0"
        private val TAG = "___"

        fun internetCheck(context: Context?): Boolean {
            if (!context?.let { isNetworkAvailable(it) }!!) {
                AlertDialog.Builder(context)
                    .setMessage("SVP vérifiez votre connexion Internet!")
                    .setCancelable(false)
                    .setTitle("Connexion Internet")
                    .setNegativeButton(
                        "Ok"
                    ) { dialog, id -> }
                    .show()
            } else if (!vpn(context)) {
                AlertDialog.Builder(context)
                    .setMessage("SVP vérifiez votre connexion VPN!")
                    .setCancelable(false)
                    .setTitle("Connexion VPN")
                    .setNegativeButton(
                        "Ok"
                    ) { dialog, id -> }
                    .show()
            } else {
                internet = true
            }
            return internet
        }

        fun networkCheck(context: Context, vpncheck:Boolean): String {
            if (vpncheck==true) {
                result = if (!isNetworkAvailable(context)) {
                    "0"
                } else if (!vpn(context)) {
                    "1"
                } else {
                    "2"
                }
            }
            else {
                result = if (!isNetworkAvailable(context)) {
                    "0"
                }  else {
                    "2"
                }
            }
            return result
        }


        fun vpn(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networks = cm.allNetworks
            Log.e(TAG, "Network count: " + networks.size)
            for (i in networks.indices) {
                val caps = cm.getNetworkCapabilities(networks[i])
                Log.e(TAG, "Network " + i + ": " + networks[i].toString())
                Log.e(
                    TAG,
                    "VPN transport is: " + caps!!.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                )
                /*Log.e(
                    TAG,
                    "NOT_VPN capability is: " + caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)
                )*/
                internet = caps!!.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
            }
            return internet
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            Log.e("connectivity"," "+connectivityManager.activeNetworkInfo.toString())
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
                .isConnected
        }

/*
        @Suppress("DEPRECATION")
        fun isNetworkAvailable(context: Context): Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm != null) {

                val activeNetwork = cm.activeNetworkInfo
                if (activeNetwork != null) {
                    Log.e("connectivity",
                        (" "+activeNetwork != null && activeNetwork.isConnected).toString()
                    )
                }
                if (activeNetwork == null) {
                    Log.e("connectivity",
                        (" "+activeNetwork != null ).toString()
                    )
                }
                return activeNetwork != null && activeNetwork.isConnected
            }
            return false

        }*/
    }

}