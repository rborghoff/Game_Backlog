package com.example.gamebacklog.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gamebacklog.R

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add2.*

class AddActivity : AppCompatActivity() {
 private lateinit var addActivityViewModel: AddActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add2)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Add Backlogitem"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
        }
    private fun initViews(){
        fab.setOnClickListener{
            addActivityViewModel.backlog.value?.apply {
                title = tvTitle.text.toString()
                platform = tvConsole.text.toString()
                dag = etDag.text.toString().toInt()
                maand = etMaand.text.toString()
                jaar = etJaar.text.toString().toInt()
            }
            addActivityViewModel.updateBacklog()
        }
    }

    private fun initViewModel(){
        addActivityViewModel = ViewModelProvider(this).get(AddActivityViewModel::class.java)
        addActivityViewModel.backlog.value = intent.extras?.getParcelable(EXTRA_ITEM)

        addActivityViewModel.backlog.observe(this, Observer {
            backlog -> if (backlog != null){
            tvTitle.setText(backlog.title)
            tvConsole.setText(backlog.platform)
            etDag.setText(backlog.dag)
            etMaand.setText(backlog.maand)
            etJaar.setText(backlog.jaar)
        }
        })
        addActivityViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
        addActivityViewModel.succes.observe(this, Observer { succes ->
            if (succes) finish()
        })


    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    companion object {
        const val EXTRA_ITEM = "EXTRA_ITEM"
    }
    }


