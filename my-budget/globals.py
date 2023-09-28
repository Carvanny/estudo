import pandas as pd
import os

if ("df_despesas.csv" in os.listdir()) and ("df_receitas.csv" in os.listdir()) and ("df_investimentos.csv" in os.listdir()):
    df_despesas = pd.read_csv("df_despesas.csv", index_col=0, parse_dates=True)
    df_receitas = pd.read_csv("df_receitas.csv", index_col=0, parse_dates=True)
    df_investimentos = pd.read_csv("df_investimentos.csv", index_col=0, parse_dates=True)
    df_despesas["Data"] = pd.to_datetime(df_despesas["Data"])
    df_receitas["Data"] = pd.to_datetime(df_receitas["Data"])
    df_investimentos["Data"] = pd.to_datetime(df_investimentos["Data"])
    df_despesas["Data"] = df_despesas["Data"].apply(lambda x: x.date())
    df_receitas["Data"] = df_receitas["Data"].apply(lambda x: x.date())
    df_investimentos["Data"] = df_investimentos["Data"].apply(lambda x: x.date())

else:
    data_structure = {'Valor':[],
        'Efetuado':[],
        'Fixo':[],
        'Data':[],
        'Categoria':[],
        'Descrição':[],}

    df_receitas = pd.DataFrame(data_structure)
    df_despesas = pd.DataFrame(data_structure)
    df_investimentos = pd.DataFrame(data_structure)
    df_despesas.to_csv("df_despesas.csv")
    df_receitas.to_csv("df_receitas.csv")
    df_investimentos.to_csv("df_investimentos.csv")


if ("df_cat_receita.csv" in os.listdir()) and ("df_cat_despesa.csv" in os.listdir()) and ("df_cat_investimentos.csv" in os.listdir()):
    df_cat_receita = pd.read_csv("df_cat_receita.csv", index_col=0)
    df_cat_despesa = pd.read_csv("df_cat_despesa.csv", index_col=0)
    df_cat_investimentos = pd.read_csv("df_cat_investimentos.csv", index_col=0, parse_dates=True)    
    cat_receita = df_cat_receita.values.tolist()
    cat_despesa = df_cat_despesa.values.tolist()
    cat_investimentos = df_cat_investimentos.values.tolist()

else:    
    cat_receita = {'Categoria': ["Salário, Benefícios Alimentação, Benefícios Vale Refeição, Investimentos Cripto, Investimentos Tradiciona"]}
    cat_despesa = {'Categoria': ["Cartão de Crédito Inter,Claro Net, Vivo Internet,Escola Elite, Material Elite, Aluguel apto, Andreia faxina, Faculdade Thiago, SPA Divida ativa, Partido Novo"]}
    cat_investimentos = {'Categoria': ["Ações, Opções, Fundos Imobiliários, Criptomoeda"]}

    df_cat_receita = pd.DataFrame(cat_receita, columns=['Categoria'])
    df_cat_despesa = pd.DataFrame(cat_despesa, columns=['Categoria'])
    df_cat_investimentos = pd.DataFrame(cat_investimentos, columns=['Categoria'])
    df_cat_receita.to_csv("df_cat_receita.csv")
    df_cat_despesa.to_csv("df_cat_despesa.csv")
    df_cat_investimentos.to_csv("df_cat_investimentos.csv")
