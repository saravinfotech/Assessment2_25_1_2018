package uitestapp.com.uitestapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Fragment with input field and button
 */
public class Screen1 extends Fragment implements View.OnClickListener {
    EditText numberInputBox;
    Button submitButton;
    int userInputValue;

    public Screen1() {
    }

    /**
     * Currently not used in the app but if any enhancements to be done
     * this would be used for saving the state of the fragment for orientation change
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!TextUtils.isEmpty(numberInputBox.getText())) {
            outState.putInt(Constant.USER_INPUT_VALUE, Integer.parseInt(numberInputBox.getText().toString()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userInputValue = bundle.getInt(Constant.FRAGMENT_BUNDLE);
        }
        View rootView = inflater.inflate(R.layout.fragment_landing, container, false);
        numberInputBox = rootView.findViewById(R.id.screen1_number_input);
        submitButton = rootView.findViewById(R.id.screen1_submit_button);
        submitButton.setOnClickListener(this);
        divideOperation();
        return rootView;
    }

    /**
     * Operation to be carried out on the value received from fragment
     */
    private void divideOperation() {
        if (userInputValue > 0 && userInputValue < Integer.MAX_VALUE)
            numberInputBox.setText(userInputValue / 2 + "");
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.screen1_submit_button) {
            goToFragmentScreen2();
        }
    }

    /**
     * Method to navigate to screen 2
     */
    private void goToFragmentScreen2() {
        Screen2 screen2 = new Screen2();
        try {
            ((MainActivity) getActivity()).customFragmentTransaction(Integer.parseInt(numberInputBox.getText().toString()), screen2, true);
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), R.string.integer_max_range_error, Toast.LENGTH_SHORT).show();
            numberInputBox.setText("");
            numberInputBox.setFocusable(true);
        }
    }


}
