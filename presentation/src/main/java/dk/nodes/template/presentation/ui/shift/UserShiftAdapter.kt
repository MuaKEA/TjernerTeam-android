package dk.nodes.template.presentation.ui.shift

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.models.Shift
import dk.nodes.template.presentation.R
import kotlinx.android.synthetic.main.fragment_job.view.*
import kotlinx.android.synthetic.main.fragment_shift_end.view.*
import kotlinx.android.synthetic.main.shift_recyclerview_row.view.*
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class UserShiftAdapter(val context: Context, val recyclerviewRow: Int) : RecyclerView.Adapter<UserViewHolder>() {
    var onItemClickedListener: ((facebookUser: FacebookUser) -> Unit?)? = null
    val facebookUser: ArrayList<FacebookUser> = ArrayList()

    override fun getItemCount(): Int {
        return facebookUser.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(recyclerviewRow, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.fullName?.text = facebookUser[position].fullName

        holder.root.setOnClickListener {
            onItemClickedListener?.invoke(facebookUser.get(position))
        }

    }

    fun addShifts(list: ArrayList<FacebookUser>) {
        facebookUser.clear()
        Timber.e(list.toString())
        facebookUser.addAll(list)
    }

}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val root = view.user_shift_cardview
    val fullName = view.user_name

}
