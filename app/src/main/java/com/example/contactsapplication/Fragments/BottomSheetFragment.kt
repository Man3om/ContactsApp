package com.example.contactsapplication.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.contactsapplication.Adapter.contactDM
import com.example.contactsapplication.databinding.FragmentBottomSheetBinding


class BottomSheetFragment : DialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    var saveContact: ((contactDM) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watcher(_binding!!.UserEmailEditText, _binding!!.UserEmailID)
        watcher(_binding!!.UserNameEditText, _binding!!.UserNameID)
        watcher(_binding!!.UserPhoneEditText, _binding!!.UserPhoneID)

        _binding!!.SaveButton.setOnClickListener {
            saveContact
            dismiss()
        }
    }

    private fun watcher(editText: EditText, textInputLayout: TextView){

        editText.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    textInputLayout.text = p0?.trim().toString()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                }

            }
        )

    }


}