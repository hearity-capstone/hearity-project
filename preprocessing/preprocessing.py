# import libraries
import pandas as pd
import os
import logging

# setup logging configuration
logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s - %(levelname)s - %(message)s')

# define functions
def load_data(file_path: str) -> pd.DataFrame:
     """Load data from csv file."""
     
     if not os.path.isfile(file_path):
          logging.error(f'File not found at {file_path}.')
          return None
     data = pd.read_csv(file_path, parse_dates=['date'])
     logging.info(f'Data loaded with shape: {data.shape}.')
     return data

def transform_data(df: pd.DataFrame, features: list) -> pd.DataFrame:
     """Transform data based on the selected features."""
     
     df = df[features]
     logging.info('Transformation completed.')
     return df

def upsampling_data(df: pd.DataFrame) -> pd.DataFrame:
     """Upsampling test results to create daily data for each patient."""
     
     upsampling_data = []
     for patient_id, group in df.groupby('patient_id'):
          group = group.set_index('date')
          daily_group = group.resample('D').interpolate(method='linear')
          daily_group = daily_group.round(2)
          
          daily_group.reset_index(inplace=True)
          daily_group['patient_id'] = patient_id
          upsampling_data.append(daily_group)
          
     df = pd.concat(upsampling_data, ignore_index=True)
     logging.info(f'Upsampling test results completed.')
     return df


if __name__ == '__main__':
     
     # define data path
     file_path = os.path.join('data', 'hearing_tests.csv')
     
     # load data
     data = load_data(file_path)
     
     if isinstance(data, pd.DataFrame): 
          # select patient randomly as a sample
          data = data[data.patient_id == 50]
          
          transformed_data = transform_data(data, features=
                    ['patient_id',
                    'date',
                    'left_freq_500_hz',
                    'left_freq_1000_hz',
                    'left_freq_2000_hz',
                    'left_freq_4000_hz',
                    'right_freq_500_hz',
                    'right_freq_1000_hz',
                    'right_freq_2000_hz',
                    'right_freq_4000_hz'])
          
          resample_data = upsampling_data(transformed_data)
                    
          # save csv file
          file_path = os.path.join('data','main.csv')
          resample_data.to_csv(file_path, index=False)
          print('csv file saved')
     else:
          logging.warning(f'{type(data)}')
