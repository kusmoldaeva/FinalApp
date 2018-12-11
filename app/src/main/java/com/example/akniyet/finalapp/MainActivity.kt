package com.example.akniyet.finalapp
import android.app.SearchManager
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_contact.view.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit



class MainActivity : AppCompatActivity() {

    private lateinit var Adapter: RecyclerView.Adapter<*>
    private var contactList: MutableList<Contact>? = ArrayList()
    private lateinit var database: Database
    private var fabBtn : FloatingActionButton? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager : RecyclerView.LayoutManager
    private lateinit var viewHolder: ViewHolder
   
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        if (savedInstanceState != null) contactList = savedInstanceState.getParcelableArrayList("List")
        else
            contactList = ArrayList()

        if (contactList != null)
            adapter = adapter(contactList!!)

        fabBtn = findViewById<View>(R.id.FloatingButton) as FloatingActionButton
        fabBtn!!.setOnClickListener {
            contactList!!.add(Contact("15BDldc", "AAA", "87074526565", "5622000", "51561"))
            adapter!!.notifyDataSetChanged()
        }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                (query)
            }
        }
        fabBtn.setOnClickListener {
            val intent = Intent (this, AddActivity::class.java)
            intent.putExtra("type", "add")
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        //List from room
        database.itemDao().getItems().subscribe {
            
                
            contactList.addAll(it)
        }
        
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            contactList = savedInstanceState.getParcelableArrayList("contact")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (contactList != null && !contactList!!.isEmpty())
            outState.putParcelableArrayList("contacts", contactList as ArrayList<out Parcelable>?)
    }
    class adapter(private val contactList: List<Contact>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var mName: TextView
            var mMobile: TextView
            val mHome: TextView
            val mWork: TextView
            val mID: TextView

            init {
                mID = itemView.findViewById(R.id.mID)
                mName = itemView.findViewById(R.id.textName)
                mMobile = itemView.findViewById(R.id.textMobile)
                mHome = itemView.findViewById(R.id.mHome)
                mWork = itemView.findViewById(R.id.textWork)

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_info, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        
            
        }

        override fun getItemCount() = contactList.size
    }
}

