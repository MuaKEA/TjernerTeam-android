package dk.nodes.template.presentation.ui.shift

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dk.nodes.template.models.Shift
import kotlinx.android.synthetic.main.shift_recyclerview_row.view.*
import timber.log.Timber


class ShiftOverviewAdapter(val context: Context, val recyclerviewRow : Int) : RecyclerView.Adapter<ViewHolder>() {
    var onItemClickedListener: ((shift: Shift) -> Unit?)? = null
    val shifts: ArrayList<Shift> = ArrayList()

    override fun getItemCount(): Int {
        return shifts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(recyclerviewRow, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.customerName?.text = shifts[position].customerName
        holder.eventDate?.text = shifts[position].eventDate.toString()
        holder.salary?.text = "DKK " + shifts[position].salary.toString()
        holder.employee_type?.text = shifts[position].employeeType?.toUpperCase()
        holder.eventDuration?.text = shifts[position].eventStart + " - " + shifts[position].eventEnd
        holder.address?.text = shifts[position].address
        holder.eventDescription?.text = shifts[position].eventDescription
        holder.root.setOnClickListener {
            onItemClickedListener?.invoke(shifts.get(position))
        }

    }

    fun addShifts(list: ArrayList<Shift>) {
        shifts.clear()
        Timber.e(list.toString())
        shifts.addAll(list)
    }
}


class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val root = view.customer_name
    val customerName = view.customer_name
    val eventDate = view.event_date
    val salary = view.salary
    val employee_type = view.employee_type
    val eventDuration = view.event_duration
    val address = view.address
    val eventDescription = view.event_description
}