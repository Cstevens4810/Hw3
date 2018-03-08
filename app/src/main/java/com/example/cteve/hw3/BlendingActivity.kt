package com.example.cteve.hw3

import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceView
import android.widget.SeekBar.OnSeekBarChangeListener
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.View
import android.widget.*
import java.io.*

/**
 * Created by csteve on 3/7/2018.
 */
class BlendingActivity : AppCompatActivity(), OnSeekBarChangeListener{

    private var o_text=""
    private var surfaceView1: SurfaceView? = null
    private var surfaceView2: SurfaceView? = null

    private var colorBar: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blending)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        surfaceView1 = findViewById(R.id.surfaceView1)
        surfaceView2 = findViewById(R.id.surfaceView2)

        colorBar = findViewById(R.id.colorBar)

        colorBar!!.setOnSeekBarChangeListener(this)

        colorBar!!.max = 100

        /*val loadColor1 = findViewById<Button>(R.id.load1)
        loadColor1.setOnClickListener(View.OnClickListener
        {
            Toast.makeText(this,"Creating Intent",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.extras.putInt("color",1)
            startActivityForResult(intent,1)

        }*/
            /*val builder = AlertDialog.Builder(this)
            builder.setTitle("Name of Color to Load")

            val input = EditText(this)
            builder.setView(input)

            //Toast.makeText(this,"this is potato",Toast.LENGTH_SHORT).show()
            builder.setPositiveButton("OK")
            {dialog, which -> o_text = input.text.toString()

                val FILENAME = o_text +".txt"
                Toast.makeText(this,FILENAME,Toast.LENGTH_SHORT).show()

                try
                {
                    //Toast.makeText(this,"trying to open " + FILENAME,Toast.LENGTH_SHORT).show()

                    var file = InputStreamReader(openFileInput(FILENAME))
                    var br = BufferedReader(file)
                    //Toast.makeText(this,"reading from " + FILENAME,Toast.LENGTH_SHORT).show()
                    var values= br.readLine()
                    //Toast.makeText(this,values.toString(),Toast.LENGTH_SHORT).show()

                    var colors: List<String> = values.toString().split(",")


                    br.close()
                    file.close()

                    var red: Int
                    var green: Int
                    var blue: Int
                    red = colors[0].toInt()
                    green = colors[1].toInt()
                    blue = colors[2].toInt()
                    //val inValues = FileInputStream(file).bufferedReader().use{it.readText}
                    var color: Int
                    color = (0xff000000 + red * 0x10000 + green * 0x100 + blue).toInt()
                    surfaceView2!!.setBackgroundColor(color)




                }catch
                (e: IOException){ }
            }
            builder.setNegativeButton("Cancel")
            { dialog, which -> dialog.cancel() }
            builder.show()
        }
        )*/


        val loadColor2 = findViewById<Button>(R.id.load2)
        loadColor2.setOnClickListener(View.OnClickListener
        {

            val intent = Intent(this, MainActivity::class.java)
            intent.extras.putInt("color", 2)
            startActivityForResult(intent, 2)
        }
           /* val builder = AlertDialog.Builder(this)
            builder.setTitle("Name of Color to Load")

            val input = EditText(this)
            builder.setView(input)

            //Toast.makeText(this,"this is potato",Toast.LENGTH_SHORT).show()
            builder.setPositiveButton("OK")
            {dialog, which -> o_text = input.text.toString()

                val FILENAME1 = o_text +".txt"
                Toast.makeText(this,FILENAME1,Toast.LENGTH_SHORT).show()

                try
                {
                    //Toast.makeText(this,"trying to open " + FILENAME,Toast.LENGTH_SHORT).show()

                    var file1 = InputStreamReader(openFileInput(FILENAME1))
                    var br1 = BufferedReader(file1)
                    //Toast.makeText(this,"reading from " + FILENAME,Toast.LENGTH_SHORT).show()
                    var values1= br1.readLine()
                    //Toast.makeText(this,values.toString(),Toast.LENGTH_SHORT).show()

                    var colors1: List<String> = values1.toString().split(",")


                    br1.close()
                    file1.close()

                    var red1: Int
                    var green1: Int
                    var blue1: Int
                    red1 = colors1[0].toInt()
                    green1 = colors1[1].toInt()
                    blue1 = colors1[2].toInt()
                    //val inValues = FileInputStream(file).bufferedReader().use{it.readText}
                    var color1: Int
                    color1 = (0xff000000 + red1 * 0x10000 + green1 * 0x100 + blue1).toInt()
                    surfaceView1!!.setBackgroundColor(color1)




                }catch
                (e: IOException){ }
            }
            builder.setNegativeButton("Cancel")
            { dialog, which -> dialog.cancel() }
            builder.show()
        }*/
            )
    }

    fun onClick1(view: View)
    {
        Toast.makeText(this,"Creating Intent",Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.extras.putInt("color",1)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if(resultCode == Activity.RESULT_OK && requestCode == 1)
        {
            val info = intent.extras
            val color = info.getInt("color")
            var surfaceOne = findViewById<SurfaceView>(R.id.surfaceView1)

            surfaceOne.setBackgroundColor(color)
        }

        else if(resultCode == Activity.RESULT_OK && requestCode == 2)
        {
            val info = intent.extras
            val color = info.getInt("color")
            var surfaceTwo = findViewById<SurfaceView>(R.id.surfaceView2)

            surfaceTwo.setBackgroundColor(color)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean)
    {
        true

    }

    override fun onStartTrackingTouch(p0: SeekBar?)
    {

    }

    override fun onStopTrackingTouch(p0: SeekBar?)
    {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        val save = menu!!.findItem(R.id.action_save)
        save.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_blending ->{
            val intent = Intent(this, BlendingActivity::class.java)
            this.startActivity(intent)
            true

        }

        R.id.action_save -> {

            true
        }

        R.id.action_main->{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true

        }

        R.id.action_load->
        {
            /*val builder = AlertDialog.Builder(this)
            builder.setTitle("Name of Color to Load")

            val input = EditText(this)
            builder.setView(input)

            //Toast.makeText(this,"this is potato",Toast.LENGTH_SHORT).show()
            builder.setPositiveButton("OK")
            {dialog, which -> o_text = input.text.toString()

                val FILENAME = o_text +".txt"
                Toast.makeText(this,FILENAME,Toast.LENGTH_SHORT).show()

                try
                {
                    //Toast.makeText(this,"trying to open " + FILENAME,Toast.LENGTH_SHORT).show()

                    var file = InputStreamReader(openFileInput(FILENAME))
                    var br = BufferedReader(file)
                    //Toast.makeText(this,"reading from " + FILENAME,Toast.LENGTH_SHORT).show()
                    var values= br.readLine()
                    //Toast.makeText(this,values.toString(),Toast.LENGTH_SHORT).show()

                    var colors: List<String> = values.toString().split(",")

                    redBar!!.progress = colors[0].toInt()
                    greenBar!!.progress=colors[1].toInt()
                    blueBar!!.progress=colors[2].toInt()

                    br.close()
                    file.close()

                    //val inValues = FileInputStream(file).bufferedReader().use{it.readText}





                }catch
                (e: IOException){ }
            }
            builder.setNegativeButton("Cancel")
            { dialog, which -> dialog.cancel() }
            builder.show()*/
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}