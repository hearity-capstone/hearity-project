# **Time Series Forecasting Pipeline with BigQuery and Apache Airflow**

This project automates a data pipeline using Google Cloud Platform (GCP) services like BigQuery and Apache Airflow to extract, transform, and load (ETL) data between BigQuery datasets for daily time series forecasting.

## **Workflow Overview**
* **Data Extraction**: Raw data is extracted from a BigQuery table.
* **Data Transformation**: Transform the data into a DataFrame with feature selection.
* **Resampling**: Gaps in the irregularly spaced data are filled using upsampling to ensure consistent temporal resolution.
* **Forecasting**: A Convolution1D-based model predicts hearing test outcomes, such as frequencies and thresholds, for each ear.
* **Data Loading**: Transformed and forecasted data is stored in a new BigQuery table.

# **Key Features**
* **Scalable Design**: Uses BigQuery for seamless handling of large datasets.
* **Flexible Orchestration**: Apache Airflow ensures modular and maintainable workflows.
* **Automated Scheduling**: Daily execution at 00:00 GMT+7 guarantees up-to-date results.
* **Data Consistency**: Preprocessing fills gaps in raw data, improving model accuracy.