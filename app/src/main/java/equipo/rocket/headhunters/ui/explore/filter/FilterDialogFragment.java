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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import equipo.rocket.headhunters.R;
import equipo.rocket.headhunters.ui.explore.ExploreFragment;


public class FilterDialogFragment extends DialogFragment {


    // TODO: Rename and change types of parameters
    private JsonObject selectedFilters;

    public FilterDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_filter_dialog,null))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Send fetch event to explore fragment
                        listener.onDialogPositiveClick(FilterDialogFragment.this);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogNegativeClick(FilterDialogFragment.this);//clear selected filters
            }
        });
        return builder.create();
    }

    public void onInvestmentRangeChange(View view){
        JsonObject investmentRange = new JsonObject();


        if(view instanceof EditText)
        {
            ((RadioGroup) getView().findViewById(R.id.investmentRadioGroup)).clearCheck();
            investmentRange.addProperty("lowBound",((EditText) view).getText().toString());
            investmentRange.addProperty("highBound",Integer.MAX_VALUE);
        }else{
            ((EditText) getView().findViewById(R.id.investmentTextArea_lowBound)).setText("");
            ((EditText) getView().findViewById(R.id.investmentTextArea_lowBound)).setText("");
            boolean checked = ((RadioButton) view).isChecked();
            switch (view.getId()){
                case R.id.lowInvestment:
                    if(checked){
                        investmentRange.addProperty("lowBound",0);
                        investmentRange.addProperty("highBound",Integer.MAX_VALUE);
                    }
                    break;
                case R.id.midInvestment:
                    if(checked){
                        investmentRange.addProperty("lowBound",((EditText) view).getText().toString());
                        investmentRange.addProperty("highBound",Integer.MAX_VALUE);
                    }
                    break;
                case R.id.highInvestment:
                    if(checked){
                        investmentRange.addProperty("lowBound",1);
                        investmentRange.addProperty("highBound",Integer.MAX_VALUE);
                    }
                    break;
            }
        }
        selectedFilters.add("investmentRange",investmentRange);

    }

    public JsonObject getSelectedFilters(){
        return selectedFilters;
    }

    public void clearSelectedFilters(){
        ((RadioGroup) getView().findViewById(R.id.investmentRadioGroup)).clearCheck();
        ((EditText) getView().findViewById(R.id.investmentTextArea_lowBound)).setText("");
        ((EditText) getView().findViewById(R.id.investmentTextArea_lowBound)).setText("");
        selectedFilters = null;
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

            Log.d(this.getClass().getSimpleName(), this.getContext()+"ffffAAAAFFFFff!!!!");
            listener = (FilterDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()
                    + " must implement FilterDialogListener");
        }
    }
}