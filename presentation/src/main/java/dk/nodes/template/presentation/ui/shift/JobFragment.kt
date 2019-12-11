package dk.nodes.template.presentation.ui.shift

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.nodes.template.models.Shift

import dk.nodes.template.presentation.R
import kotlinx.android.synthetic.main.job_fragment.*
import kotlinx.android.synthetic.main.shift_recyclerview_row.*
import java.time.LocalDate

private const val shiftArg = "shift"


class JobFragment : Fragment() {

    var shift: Shift? = null

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.job_fragment, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            shift = it.getParcelable(shiftArg)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = LocalDate.parse(shift?.eventDate)

        val dateWeekDay = date.dayOfWeek.toString().substring(0, 3) + "."
        val dateMonth = date.monthValue.toString()
        val dateMonthDay = date.dayOfMonth.toString()


        costumer_name?.text = shift?.customerName
        address_city?.text = shift?.city
        event_date_month2?.text = dateMonth
        event_date_monthday2?.text = dateMonthDay
        event_date_weekday2?.text = dateWeekDay
        salary2?.text = "DKK " + shift?.salary?.toBigDecimal()?.setScale(2).toString()
        paymentDate?.text = shift?.paymentDate
        event_type?.text = shift?.eventName
        employee_type2?.text = shift?.employeeType
        event_description2?.text = shift?.eventDescription
        address2?.text = shift?.address
        dress_code?.text = shift?.dressCode
        staffFood?.text = shift?.staffFood
        overtime?.text = shift?.overtime
        event_duration_txt?.text = shift?.startTime + " - " + shift?.endTime

        transport?.text = shift?.transportSupplements.toString()
        if (transport.text == "true"){
            transport.text = getString(R.string.tillaeg)
        } else {
            transport.text = getString(R.string.intet_tillaeg)
        }


    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(shift: Shift) =
                JobFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(shiftArg, shift)

                    }

                }
    }
}

