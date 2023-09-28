from datetime import datetime, timedelta
from dateutil.relativedelta import relativedelta # pip install python-dateutil

hoje = datetime.today()
dia_da_semana = hoje.weekday() # 0=segunda, 1=terça, etc
dia = hoje.day

data = datetime(hoje.year, hoje.month, 1) # dia 1 do mês atual
if (dia_da_semana == 0 and dia in (1, 2, 3)) or (dia_da_semana in (1, 2, 3, 4) and dia == 1):
    # hoje é o primeiro dia útil do mês
    # muda a data para o mês anterior
    data -= relativedelta(months=1)

dt = data.strftime('%d/%m/%Y')
print(dt)