from sqlalchemy import insert, create_engine, MetaData, Table
from sqlalchemy.pool import NullPool
import psycopg2
import json
import os

def get_connection():
    return psycopg2.connect(
        host=os.getenv("POSTGRES_HOST"),
        port=5432,
        database=os.getenv("POSTGRES_DATABASE"),
        user=os.getenv("POSTGRES_USER"),
        password=os.getenv("POSTGRES_PASSWORD")
    )

with open('./results/results.json','r') as f:
  data = json.loads(f.read())

engine = create_engine('postgresql+psycopg2://', creator=get_connection, poolclass=NullPool)


metadata = MetaData()
metadata.reflect(bind=engine)

with engine.begin() as connection:
    for key in data[0]:
        table = Table(key, metadata, autoload_with=engine)
        stmt = insert(table).values(data[0][key])
        connection.execute(stmt)
