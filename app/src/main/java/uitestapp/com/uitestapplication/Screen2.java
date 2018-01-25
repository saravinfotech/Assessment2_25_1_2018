package uitestapp.com.uitestapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Fragment class for screen 2
 */
public class Screen2 extends Fragment implements View.OnClickListener {

    EditText numberInputBox;
    Button submitButton;
    int userInputValue;

    public Screen2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userInputValue = bundle.getInt(Constant.FRAGMENT_BUNDLE);
        }
        View rootView = inflater.inflate(R.layout.fragment_fragment_screen2, container, false);
        numberInputBox = rootView.findViewById(R.id.screen2_number_input);
        submitButton = rootView.findViewById(R.id.screen2_submit_button);
        submitButton.setOnClickListener(this);
        multiplyOperation();
        return rootView;
    }

    /**
     * Operation to be carried out on the received value
     */
    public void multiplyOperation() {
        if (userInputValue > 0 && userInputValue < Integer.MAX_VALUE)
            numberInputBox.setText(userInputValue * 2 + "");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.screen2_submit_button) {
            goToScreen1();
        }
    }

    /**
     * Method to navigate back to screen 1
     */
    private void goToScreen1() {
        Screen1 screen1 = new Screen1();
        try {
            ((MainActivity) getActivity()).customFragmentTransaction(Integer.parseInt(numberInputBox.getText().toString()), screen1, false);
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), R.string.integer_max_range_error, Toast.LENGTH_SHORT).show();
            numberInputBox.setText("");
            numberInputBox.setFocusable(true);
        }
    }

}
