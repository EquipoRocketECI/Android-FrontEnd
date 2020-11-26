package equipo.rocket.headhunters.ui.explore.filter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.ui.explore.ExploreFragment;


public class FilterDialogFragment extends DialogFragment {


    // TODO: Rename and change types of parameters
    private JsonObject selectedFilters;
    private JsonObject investmentRange;
    private JsonObject categories;

    private RadioGroup investmentRadioGroup;

    private EditText investmentLowBound;
    private EditText investmentHighBound;


    public FilterDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.fragment_filter_dialog,null);

        selectedFilters = new JsonObject();
        investmentRange = new JsonObject();

        investmentRadioGroup = view.findViewById(R.id.investmentRadioGroup);
        investmentRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            onRadioGroupInvestmentRangeChange(group,checkedId);
        });
        investmentLowBound = view.findViewById(R.id.investmentTextArea_lowBound);
        investmentLowBound.setOnFocusChangeListener((v,hasFocus)->onEditTextInvestmentRangeChange(v,hasFocus));
        investmentHighBound = view.findViewById(R.id.investmentTextArea_highBound);
        investmentHighBound.setOnFocusChangeListener((v,hasFocus)->onEditTextInvestmentRangeChange(v,hasFocus));

        builder.setView(view)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    //Send fetch event to explore fragment
                    listener.onDialogPositiveClick(FilterDialogFragment.this);
                }).setNegativeButton(R.string.cancel, (dialog, which) -> {
                    listener.onDialogNegativeClick(FilterDialogFragment.this);//clear selected filters
                });
        return builder.create();
    }

    public void onEditTextInvestmentRangeChange(View view,boolean hasFocus){

        investmentRadioGroup.clearCheck();
        if(!hasFocus){
            if (view.getId() == R.id.investmentTextArea_lowBound) {
                investmentRange.addProperty("lowBound", Integer.parseInt(0+investmentLowBound.getText().toString()));
            } else if (view.getId() == R.id.investmentTextArea_highBound) {
                investmentRange.addProperty("highBound", Integer.parseInt(0+investmentHighBound.getText().toString()));
            }

        }
        selectedFilters.add("investmentRange",investmentRange);
    }

    public void onRadioGroupInvestmentRangeChange(RadioGroup group,int checkedId){

            switch (checkedId){
                case R.id.lowInvestment:
                        investmentRange.addProperty("lowBound",0);
                        investmentRange.addProperty("highBound",100000);
                    break;
                case R.id.midInvestment:
                        investmentRange.addProperty("lowBound",100000);
                        investmentRange.addProperty("highBound",250000000);
                    break;
                case R.id.highInvestment:
                        investmentRange.addProperty("lowBound",250000000);
                        investmentRange.addProperty("highBound",Integer.MAX_VALUE);
                    break;

            }
            if(checkedId != -1){
                investmentLowBound.setText("");
                investmentHighBound.setText("");
            }

        selectedFilters.add("investmentRange",investmentRange);

    }

    public JsonObject getSelectedFilters(){
        return selectedFilters;
    }

    public void clearSelectedFilters(){
        investmentRadioGroup.clearCheck();
        investmentLowBound.setText("");
        investmentHighBound.setText("");
        selectedFilters.remove("investmentRange");
    }

    public void clearEditTextFocus(){
        investmentHighBound.clearFocus();
        investmentLowBound.clearFocus();
    }

    public interface FilterDialogListener{
        public void onDialogPositiveClick(FilterDialogFragment dialog);
        public void onDialogNegativeClick(FilterDialogFragment dialog);
    }

    FilterDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {


            listener = (FilterDialogListener) getTargetFragment();

        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()
                    + " must implement FilterDialogListener");
        }
    }
}