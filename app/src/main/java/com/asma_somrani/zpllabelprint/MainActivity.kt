package com.asma_somrani.zpllabelprint

import android.app.ProgressDialog
import android.os.AsyncTask.execute
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.somraniasma.zpllabelprint.LabelPrinting
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var printbtn:Button
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printbtn=findViewById(R.id.btnprint)


        printbtn.setOnClickListener {


 val message = """^XA
^JMB^FS
^FO50,600^GB250,1,3^FS
^FO150,635^GB600,540,3^FS
^FWr
^CFA,10
^FO322,20^GB58,200,3^FS
^FO355,30^FDHello world! ^FS      :
^XZ"""

           // var progressBar=ProgressBar(this@MainActivity)
            //progressBar?.sho
            val dialog: android.app.AlertDialog? = SpotsDialog.Builder().setContext(this@MainActivity)
                .setMessage("Connecting...").build()!!
            dialog!!.show()


            var print= LabelPrinting(this@MainActivity, false, "192.168.1.109", 9100, message)

            var result=print.execute("").get()
            Log.e("RESULT "," "+result)
            if (result != null && result.equals("2")) {
                //dialog.dismiss()
                AlertDialog.Builder(this)
                    .setMessage("SVP vérifiez votre connexion Internet!")
                    .setCancelable(false)
                    .setTitle("Connexion Internet")
                    .setNegativeButton("Ok") { dialog, id -> }
                    .show()
            } else if (result != null && result.equals("1")) {
             //   dialog.dismiss()
                AlertDialog.Builder(this)
                    .setMessage("SVP vérifiez votre connexion VPN!")
                    .setCancelable(false)
                    .setTitle("Connexion VPN")
                    .setNegativeButton("Ok") { dialog, id -> }
                    .show()

            } else if (result != null && result.equals("3")) {
            //    dialog.dismiss()
                AlertDialog.Builder(this)
                    .setMessage("Merci de valider la configuration imprimante au niveau de l'interface Setting")
                    .setCancelable(false)
                    .setTitle("Parametres imprimante")
                    .setNegativeButton("Ok") { dialog, id -> }
                    .show()

            }
/*
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

            }*/}
    }
}