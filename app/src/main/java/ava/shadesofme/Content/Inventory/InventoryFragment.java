package ava.shadesofme.Content.Inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import ava.shadesofme.Content.ContentFragment;
import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.R;
import ava.shadesofme.databinding.FragmentInventoryBinding;

public class InventoryFragment extends ContentFragment implements AdapterView.OnItemClickListener {

    private InventoryViewModel viewModel;

    public InventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        viewModel = getArguments().getParcelable("Inventory");
        FragmentInventoryBinding binding = FragmentInventoryBinding.bind(view);
        binding.setInventoryVM(viewModel);

        LinearLayout.LayoutParams fragmentSize = calculateFragmentSize();
        binding.containerFragmentContent.setLayoutParams(fragmentSize);

        binding.gridEquipmentSlots.setAdapter(new SimpleAdapter(getContext(), viewModel.getItems(),
                R.layout.equipment_slot, new String[] {"item_name", "item_weight", "item_volume"},
                new int[] {R.id.text_slot_item_name, R.id.text_slot_item_weight, R.id.text_slot_item_volume}));

        binding.gridEquipmentSlots.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.ll_equipment_slot) {
            String itemName = ((TextView) view.findViewById(R.id.text_slot_item_name)).getText().toString();
            viewModel.itemClicked(itemName);
        }
    }

    public ContentViewModel getViewModel() {
        return viewModel;
    }
}
