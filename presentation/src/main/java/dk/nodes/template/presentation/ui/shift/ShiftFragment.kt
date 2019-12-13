package dk.nodes.template.presentation.ui.shift

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dk.nodes.template.models.FacebookUser
import dk.nodes.template.models.Shift
import dk.nodes.template.presentation.R
import kotlinx.android.synthetic.main.fragment_job.*
import kotlinx.android.synthetic.main.fragment_shift_end.*
import net.hockeyapp.android.metrics.model.User

import java.time.LocalDate

private const val shiftArg = "FacebookUser"


class ShiftFragment : Fragment() {

    var facebookUser: FacebookUser? = null

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shift_end, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            facebookUser = it.getParcelable(shiftArg)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_name.text = facebookUser?.fullName
        //edit_icon.text = facebookUser?.picture

        //val date = LocalDate.parse(shift?.eventDate)

        //val dateWeekDay = date.dayOfWeek.toString().substring(0, 3) + "."
        //val dateMonth = date.monthValue.toString()
        //val dateMonthDay = date.dayOfMonth.toString()


        //costumer_name_txt?.text = shift?.customerName
        //address_city_txt?.text = shift?.city
        //event_date_month_txt?.text = dateMonth
        //event_date_monthday_txt?.text = dateMonthDay
        //event_date_weekday_txt?.text = dateWeekDay
        // salary_txt?.text = "DKK " + shift?.salary?.toBigDecimal()?.setScale(2).toString()
        //payment_date_txt?.text = shift?.paymentDate
        //event_type_txt?.text = shift?.eventName
        //employee_type_txt?.text = shift?.employeeType
        //event_description_txt?.text = shift?.eventDescription
        //address_txt?.text = shift?.address
        //dress_code_txt?.text = shift?.dressCode
        //staff_food_txt?.text = shift?.staffFood
        //overtime_txt?.text = shift?.overtime
        //event_duration_txt?.text = shift?.startTime + " - " + shift?.endTime

        //transport_txt?.text = shift?.transportSupplements.toString()
        //if (transport_txt.text == "true"){
        //    transport_txt.text = getString(R.string.tillaeg)
        //} else {
        //    transport_txt.text = getString(R.string.intet_tillaeg)
        //}


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
                ShiftFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(shiftArg, shift)

                    }

                }
    }
}