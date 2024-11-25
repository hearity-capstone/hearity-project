# Hearing Loss Dataset
---

The **Hearing Loss Dataset** is based on a real-world scenario, reflecting on the hearing health of factory workers who were exposed to the high levels noise in heavy machinery environments. This dataset is inspired by a collaboration between a heavy equipment manufacturing company and a general hospital, where regular hearing tests were conducted over a specific time period. These tests were designed to monitor the hearing thresholds of factory workers, ensuring a thorough representation of occupational hearing loss resulting from prolonged exposure to loud environments. The primary goal was to observe how the consistent noise exposure in such settings could affect workers' hearing abilities.

* Category : Healthcare | Audiology | Occupational Health | Industrial Health and Safety
* Specificity : Occupational Audiometry Test Data | Impact of Long-Term Noise Exposure
* Containing with 22 columns and 400 rows
  
## **Dataset Schema**


| Column Name          | Description                                       | Data Type |
|----------------------|---------------------------------------------------|-----------|
| `patient_id`         | Unique ID for each patient                        | integer   |
| `first_name`         | First name of the patient                         | string    |
| `last_name`          | Last name of the patient                          | string    |
| `phone_number`       | Patient's phone number                            | integer   |
| `gender`             | Patient's gender                                  | string    |
| `address_line1`      | Patient's main address                            | string    |
| `address_line2`      | Patient's secondary address                       | string    |
| `city`               | City where the patient resides                    | string    |
| `state`              | State where the patient resides                   | string    |
| `postal_code`        | Patient's postal code                             | string    |
| `test_id`            | Unique ID for each test result                    | integer   |
| `date`               | Date of the hearing test                          | datetime  |
| `left_freq_500_hz`   | Hearing threshold of left ear at 500 Hz           | integer   |
| `left_freq_1000_hz`  | Hearing threshold of left ear at 1 kHz            | integer   |
| `left_freq_2000_hz`  | Hearing threshold of left ear at 2 kHz            | integer   |
| `left_freq_4000_hz`  | Hearing threshold of left ear at 4 kHz            | integer   |
| `right_freq_500_hz`  | Hearing threshold of right ear at 500 Hz          | integer   |
| `right_freq_1000_hz` | Hearing threshold of right ear at 1 kHz           | integer   |
| `right_freq_2000_hz` | Hearing threshold of right ear at 2 kHz           | integer   |
| `right_freq_4000_hz` | Hearing threshold of right ear at 4 kHz           | integer   |
| `AD`                 | Average hearing threshold in right ear            | float     |
| `AS`                 | Average hearing threshold in left ear             | float     |



Factory workers were required to undergo routine hearing tests, which helped detect subtle changes in their hearing, especially in certain frequencies that are typically affected by noise exposure. The data captures the real effects of noise-induced hearing degradation. Workers were often reluctant to consistently use ear protection, such as earplugs or earmuffs, during their shifts. This non-compliance contributed to a gradual decline in hearing, which is reflected in the dataset.

The structure of the dataset mirrors both the temporal progression of hearing decline and the impact of non-compliance with safety protocols. In the initial testing phase, the workers’ hearing thresholds were within the normal range, typically between 0-25 dB for both the right (AD) and left (AS) ears. However, as the tests progressed, there was a noticeable increase in hearing thresholds across certain frequencies, especially at 4k Hz, indicating the development of hearing loss over time. This decline was most evident in workers who neglected to use their ear protection consistently.

The sample data focuses on factory workers who frequently did not adhere to safety protocols, specifically the use of ear protection, during their 8-hour work shifts. These workers were exposed to noise levels exceeding 85 dB, a condition commonly found in industrial environments with heavy machinery. By the second test, the dataset clearly demonstrated significant hearing loss, particularly in the 4k Hz frequency range. This highlights the real-world impact of long-term exposure to industrial noise and representation of occupational hearing loss.