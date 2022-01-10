package com.example.challenge.ui
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.challenge.adapter.AdapterWeather
import com.example.challenge.databinding.WeatherContentBinding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WeatherFragment : Fragment() {
    private lateinit var viewModel : ViewModel
    private var _binding: WeatherContentBinding? = null
    private val binding get() = _binding!!
    private var city = "Melbourne"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WeatherContentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        binding.edtSearch.setText(city)
        var mDividerItemDecoration = DividerItemDecoration(
            binding.recycleWeather.getContext(),
            DividerItemDecoration.VERTICAL
        )
        binding.recycleWeather.addItemDecoration(mDividerItemDecoration)
        getData()
        binding.btnSearch.setOnClickListener {
            if (binding.edtSearch.text?.length?:0 > 3){
                city = binding.edtSearch.text.toString()
                getData()
            }
        }
    }

    fun getData(){
        viewModel.getWeatherDetail(city){data ->
            val adapterWeather = AdapterWeather(requireContext()){newCity ->
            }
//            adapterWeather.clearAll(data!!.list)
            adapterWeather.addAll(data!!.list)
            binding.recycleWeather.adapter = adapterWeather

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}