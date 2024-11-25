# import libraries
import random
import pandas as pd
from faker import Faker
import os


def test_result(num_records):
  """Generates specified number of test result."""
  
  for i in range(1, num_records + 1):
        patient_id = i

        # define dates with format YYYY - MM - DD
        dates = ['2022/01/15', '2022/07/17', '2023/01/15', '2023/07/27']

        # first test result
        first_test = {
            f"{side}_freq_{freq}_hz": (random.choice([0, 5, 10, 15, 20, 25]) if freq == 4000 else random.choice([0, 5, 10, 15]))
            for side in ["left", "right"]
            for freq in [500, 1000, 2000, 4000]
        }

        # second test result
        second_test = {
            key: (value + random.choice([5, 10, 15]) if key in ['left_freq_4000_hz', 'right_freq_4000_hz'] else value + random.choice([0, 5]))
            for key, value in first_test.items()
        }

        # third test result
        third_test = {
            key: value + random.choice([0, 5, 10])
            for key, value in second_test.items()
        }

        # fourth test result
        fourth_test = {
            key: value + random.choice([5, 10])
            for key, value in third_test.items()
        }

        # put all test results in a list
        tests = [first_test, second_test, third_test, fourth_test]
        test_ids = [1, 2, 3, 4]

        # yield each test result as a separate row
        for date, test_result, test_id in zip(dates, tests, test_ids):
          # calculate AD (right ear average) and AS (left ear average)
            AD = (test_result["right_freq_500_hz"] + test_result["right_freq_1000_hz"] +
                  test_result["right_freq_2000_hz"] + test_result["right_freq_4000_hz"]) / 4
            AS = (test_result["left_freq_500_hz"] + test_result["left_freq_1000_hz"] +
                  test_result["left_freq_2000_hz"] + test_result["left_freq_4000_hz"]) / 4

            patient_info = {
                "patient_id": patient_id,
                "test_id": test_id,
                "date": date,
                **test_result,
                "AD": AD,
                "AS": AS
            }
            yield patient_info


def generate_unique_male_names(num_records: int):
    """Generates a specified number of unique male names."""

    unique_names = set()
    fake = Faker()

    while len(unique_names) < num_records:
        first_name = fake.first_name_male()
        last_name = fake.last_name()
        full_name = f"{first_name} {last_name}"

        if full_name not in unique_names:
           unique_names.add(full_name)
           yield first_name, last_name


def generate_patient_data(num_records):
    """Generates patient data records."""
    
    unique_names_generator = generate_unique_male_names(num_records)
    fake = Faker()

    for i in range(1, num_records + 1):
        first_name, last_name = next(unique_names_generator)
        patient_info = {
            "patient_id": i,
            "first_name": first_name,
            "last_name": last_name,
            "phone_number": fake.msisdn(),
            "gender": "Male",
            "address_line1": fake.street_address(),
            "address_line2": fake.secondary_address(),
            "city": fake.city(),
            "state": fake.state_abbr(),
            "postal_code": fake.zipcode()
        }
        yield patient_info

if __name__ == '__main__':
  num_records = 100
  patient_df = pd.DataFrame(generate_patient_data(num_records))
  test_df = pd.DataFrame(test_result(num_records))

  df = pd.merge(patient_df, test_df, on="patient_id", how="left")

  # save csv file
  file_path = os.path.join('data','hearing_tests.csv')
  df.to_csv(file_path, index=False)
  print('csv file saved')