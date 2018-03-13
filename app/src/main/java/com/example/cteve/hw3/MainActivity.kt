package com.example.cteve.hw3

import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.support.v7.app.AlertDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceView
import android.widget.SeekBar.OnSeekBarChangeListener
import android.content.Context.MODE_PRIVATE
import android.view.View.VISIBLE
import android.widget.*
import java.io.*
import android.widget.Button



class MainActivity : AppCompatActivity(), OnSeekBarChangeListener
{
    private var s_Text=""
    private var o_text=""

    private var redNum: TextView? = null
    private var blueNum: TextView? = null
    private var greenNum: TextView? = null

    private var redBar: SeekBar? = null
    private var greenBar: SeekBar? = null
    private var blueBar: SeekBar? = null

    private var surfaceView: SurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setIcon(R.drawable.ic_color_lens_black_24dp)

        surfaceView = findViewById(R.id.surfaceView)

        redNum = findViewById(R.id.redNum)
        blueNum = findViewById(R.id.blueNum)
        greenNum = findViewById(R.id.greenNum)

        redBar = findViewById(R.id.redBar)
        greenBar = findViewById(R.id.greenBar)
        blueBar = findViewById(R.id.blueBar)

        redBar!!.max = 255
        greenBar!!.max = 255
        blueBar!!.max = 255



        redBar!!.setOnSeekBarChangeListener(this)
        greenBar!!.setOnSeekBarChangeListener(this)
        blueBar!!.setOnSeekBarChangeListener(this)

        val intent = intent

        val info = intent.extras
        if(info != null)
        {
            if(info.containsKey("color"))
            {
                val acceptColor = findViewById<Button>(R.id.acceptColor)
                acceptColor.visibility = View.VISIBLE
                acceptColor.setOnClickListener(View.OnClickListener {
                    val returnIntent = Intent(this, BlendingActivity::class.java)

                    val currentColor = (0xff000000 + redBar!!.progress * 0x10000 + greenBar!!.progress * 0x100 + blueBar!!.progress).toInt()

                    returnIntent.putExtra("color",currentColor)
                    setResult(Activity.RESULT_OK,returnIntent)
                    finish()
                })
            }
        }

    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean)
    {
        var color: Int
        when(seekBar)
        {
            redBar ->
            {
                var green = greenBar!!.progress
                var blue = blueBar!!.progress
                color = (0xff000000 + progress * 0x10000 + green * 0x100 + blue).toInt()
                redNum!!.text = progress.toString()
                surfaceView!!.setBackgroundColor(color)
            }

            greenBar->
            {
                var red = redBar!!.progress
                var blue = blueBar!!.progress
                color = (0xff000000 + red * 0x10000 + progress * 0x100 + blue).toInt()
                greenNum!!.text = progress.toString()
                surfaceView!!.setBackgroundColor(color)
            }

            blueBar->
            {
                var red = redBar!!.progress
                var green = greenBar!!.progress
                color = (0xff000000 + red * 0x10000 + green * 0x100 + progress).toInt()
                surfaceView!!.setBackgroundColor(color)
                blueNum!!.text = progress.toString()
            }
        }
    }

   /*override fun finish() {
        val intent = Intent(this, BlendingActivity::class.java)

        var currentColor = (0xff000000 + redBar!!.progress * 0x10000 + greenBar!!.progress * 0x100 + blueBar!!.progress).toInt()

        intent.extras.putInt("color",currentColor)
        setResult(Activity.RESULT_OK,intent)
        super.finish()
    }*/

    override fun onStartTrackingTouch(p0: SeekBar?)
    {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onStopTrackingTouch(p0: SeekBar?)
    {

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

        R.id.action_main->{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true

        }

        R.id.action_save -> {
            var red = redBar!!.progress
            var green = greenBar!!.progress
            var blue = blueBar!!.progress

            val  values = red.toString() + "," + green.toString() + "," + blue.toString()

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Name of Color")

            val input = EditText(this)
            builder.setView(input)


            builder.setPositiveButton("OK")
            {
                dialog, which -> s_Text = input.text.toString()

                val FILENAME = s_Text +".txt"


                try
                {
                    val file = OutputStreamWriter(openFileOutput(FILENAME,Activity.MODE_PRIVATE))
                    file.write(values)
                    file.flush()
                    file.close()


                }catch (e: IOException){
                }
                Toast.makeText(this,"Color Saved",Toast.LENGTH_SHORT).show()

            }

            builder.setNegativeButton("Cancel")
            { dialog, which -> dialog.cancel() }
            builder.show()

            true
        }

        R.id.action_load->
        {
            val builder = AlertDialog.Builder(this)
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
            builder.show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
