package dk.nodes.template.presentation.ui.shift

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dk.nodes.template.models.Shift
import dk.nodes.template.presentation.R
import kotlinx.android.synthetic.main.fragment_job_fragment.view.*
import kotlinx.android.synthetic.main.shift_recyclerview_row.view.*
import timber.log.Timber
import java.time.LocalDate
import kotlin.collections.ArrayList


class ShiftOverviewAdapter(val context: Context, val recyclerviewRow: Int) : RecyclerView.Adapter<ViewHolder>() {
    var onItemClickedListener: ((shift: Shift) -> Unit?)? = null
    val shifts: ArrayList<Shift> = ArrayList()

    override fun getItemCount(): Int {
        return shifts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(recyclerviewRow, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date = LocalDate.parse(shifts[position].eventDate)

        val dateWeekDay = date.dayOfWeek.toString().substring(0, 3).toUpperCase()
        val dateMonth = date.month.toString().substring(0, 3).toUpperCase()
        val dateMonthDay = date.dayOfMonth.toString()

        holder.customerName?.text = shifts[position].customerName
        holder.eventDateWeekDay?.text = dateWeekDay
        holder.eventDateMonthDay?.text = dateMonthDay
        holder.eventDateMonth?.text = dateMonth

        holder.salary?.text = "DKK " + shifts[position].salary?.toBigDecimal()?.setScale(2).toString()
        holder.employee_type?.text = shifts[position].employeeType?.toUpperCase()

        if (holder.employee_type.text == "TJENER") {
            holder.employee_type?.setBackgroundResource(R.drawable.waiter_rounded_corners)
        } else {
            holder.employee_type?.setBackgroundResource(R.drawable.bartender_rounded_corners)
        }

        holder.eventDuration?.text = shifts[position].startTime + " - " + shifts[position].endTime
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
    val root = view.shift_cardview
    val customerName = view.customer_name
    val eventDateWeekDay = view.event_date_weekday
    val eventDateMonthDay = view.event_date_monthday
    val eventDateMonth = view.event_date_month
    val salary = view.salary
    val employee_type = view.employee_type_field_shift
    val eventDuration = view.costumer_name
    val address = view.address_txt
    val eventDescription = view.event_description_field_shift
}