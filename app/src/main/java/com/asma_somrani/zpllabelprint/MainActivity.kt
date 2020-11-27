package com.asma_somrani.zpllabelprint

import android.os.AsyncTask.execute
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.somraniasma.zpllabelprint.LabelPrinting


class MainActivity : AppCompatActivity() {
    lateinit var printbtn:Button
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




            var print= LabelPrinting(this@MainActivity, false, "192.168.1.109", 9100, message)
            print.execute("") }
    }
}