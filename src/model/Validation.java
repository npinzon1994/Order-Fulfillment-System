package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Validation {

	public static boolean isValidEmail(ImageView image, String email) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher matcher = pattern.matcher(email);
		if (matcher.find() && matcher.group().equals(email)) {
			image.setVisible(false);
			System.out.println("Email is valid!");
			return true;
		} else {
			// existsLabel.setText("Incorrect email format!");
			System.out.println("Email invalid!");
			image.setVisible(true);
			return false;
		}
	}

	public static boolean isValidZipCode(ImageView zipView, String zip) {
		if (zip.length() == 5) {
			zipView.setVisible(false);
			System.out.println("Zip code is valid!");
			return true;
		} else if (zip.length() != 5) {
			// existsLabel.setText("Incorrect email format!");
			System.out.println("Zip code invalid!");
			zipView.setVisible(true);
			return false;
		}
		return true;
	}

	public static boolean isValidPhone(ImageView image, String phone1, String phone2, String phone3) {
		if ((phone1.length() == 3) && (phone2.length() == 3) && (phone3.length() == 4)) {
			image.setVisible(false);
			System.out.println("Phone is valid!");
			return true;
		} else {
			// image.setText("Incorrect email format!");
			image.setVisible(true);
			System.out.println("Phone invalid!");
			return false;
		}

	}

	public static boolean isValidState(ImageView image, String value) {
		if (!value.equals("State")) {
			image.setVisible(false);
			System.out.println("State is valid!");
			return true;
		} else {
			// image.setText("Incorrect email format!");
			image.setVisible(true);
			System.out.println("State invalid!");
			return false;
		}

	}

	public static boolean isValidCreditCard(ImageView image, String field1, String field2, String field3,
			String field4) {
		if (field1.length() == 4 && field2.length() == 4 && field3.length() == 4 && field4.length() == 4) {
			image.setVisible(false);
			System.out.println("Credit card is valid!");
			return true;
		} else {
			// image.setText("Incorrect email format!");
			image.setVisible(true);
			System.out.println("Credit card invalid!");
			return false;
		}

	}

	public static boolean isValidCvv(ImageView image, ComboBox<String> box, String field) {
		if ((box.getValue().equals("American Express") && field.length() == 4)
				|| (!box.getValue().equals("American Express") && field.length() == 3)) {
			image.setVisible(false);
			System.out.println("CVV is valid!");
			return true;
		} else {
			// image.setText("Incorrect email format!");
			image.setVisible(true);
			System.out.println("CVV invalid!");
			return false;
		}

	}

	public static void limitInputToPhoneField(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (field.getText().length() > 3) {
					String s = field.getText().substring(0, 3);
					field.setText(s);
				}
			}
		});

	}

	public static void limitInputToPhoneField3(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (field.getText().length() > 4) {
					String s = field.getText().substring(0, 4);
					field.setText(s);
				}
			}
		});

	}

	public static void limitInputToZipField(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (field.getText().length() > 5) {
					String s = field.getText().substring(0, 5);
					field.setText(s);
				}
			}
		});

	}

	public static void limitInputToCreditCardField(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (field.getText().length() > 4) {
					String s = field.getText().substring(0, 4);
					field.setText(s);
				}
			}
		});

	}

	public static void limitInputToCentsField(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {
				if (field.getText().length() > 2) {
					String s = field.getText().substring(0, 2);
					field.setText(s);
				}
			}
		});

	}

	public static void limitInputToNumbers(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});

	}

}
