FROM apache/airflow:2.7.2-python3.10

USER root
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    git && \
    rm -rf /var/lib/apt/lists/* 

USER airflow
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
# clean up unnecessary files (e.g., requirements.txt)
RUN rm -f requirements.txt