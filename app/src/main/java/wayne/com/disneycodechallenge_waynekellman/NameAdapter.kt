package wayne.com.disneycodechallenge_waynekellman

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(val map: MutableMap<String, Boolean>): RecyclerView.Adapter<NameAdapter.NameViewHolder>() {
    lateinit var iChecked: IsChecked
    val names: MutableList<Pair<String, Boolean>> = mutableListOf()

    fun makeList(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach { (name, isChecked) -> names.add(Pair(name, isChecked)) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guest_item, parent, false)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.onBind(names[position], iChecked)
    }

    override fun getItemCount(): Int = names.size
    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(string: Pair<String, Boolean>, iChecked: IsChecked) {
            itemView.findViewById<TextView>(R.id.name).text = string.first
            itemView.findViewById<CheckBox>(R.id.name_check).setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    iChecked.isChecked(string.first)
                } else {
                    iChecked.isUnchecked(string.first)
                }
            }
        }
    }
}