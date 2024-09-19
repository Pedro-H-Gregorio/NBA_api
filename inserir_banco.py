from sqlalchemy import insert, create_engine, MetaData, Table, select, update
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

def upsert_manual(tabela, valores, chave_primaria, conn):
        trans = conn.begin()
        try:
          for valor in valores:
            chave_valor = valor[chave_primaria]
            select_stmt = select([tabela]).where(tabela.c[chave_primaria] == chave_valor)
            result = conn.execute(select_stmt).fetchone()

            if result is None:
                insert_stmt = insert(tabela).values(valor)
                conn.execute(insert_stmt)
            else:
                update_stmt = update(tabela).where(tabela.c[chave_primaria] == chave_valor).values(valor)
                conn.execute(update_stmt)
          trans.commit()
        except Exception as e:
            trans.rollback()
            raise e

engine = create_engine('postgresql+psycopg2://', creator=get_connection, poolclass=NullPool)


metadata = MetaData()
metadata.reflect(bind=engine)

with engine.begin() as connection:
    for key in data[0]:
        table = Table(key, metadata, autoload_with=engine)
        upsert_manual(table, data[0][key], "id", connection)
