package com.example.gamebacklog.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Backlog

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.backlog_item_view_test.*

class MainActivity : AppCompatActivity() {
    private val backlog = arrayListOf<Backlog>()
    private val backlogAdapter = BacklogAdapter(backlog)
    private lateinit var viewModel: MainActivityViewModel

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews(){
        fab.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_ITEM, mainActivityViewModel.backlog.value)
            startActivity(intent)
        }
    }
    private fun initViewModel(){
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.backlog.observe(this, Observer { backlog ->
            if(backlog != null){
                tvTitle.text= backlog.title
                tvConsole.text = backlog.platform
                tvDate.text = getString(R.string.date,backlog.dag,backlog.maand,backlog.jaar)
            }
        })
    }

}
