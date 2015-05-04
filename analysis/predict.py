import pandas as pd
import statsmodels.api as sm
import numpy as np
import matplotlib.pyplot as pl



df = pd.read_csv("MUST2.csv")
df.columns = ["arch", "height", "distM", "distP", "distB", "countM", 'countP', 'countB']
data = df

data['intercept'] = 1.0

train = data[:400]
test = data[400:]

pl1 = train.ix[train['arch'] == 1]
pl0 = train.ix[train['arch'] == 0]

train_cols = ['height', 'distB', 'distM', 'distP', 'countP']
logit = sm.Logit(train['arch'], train[train_cols])
result = logit.fit()

print result.summary()
# print result.conf_int()

# Height/mark correlation
# pl.plot(pl1['distP'], pl1['countP'], 'ro', color='r')
pl.plot(pl0['distP'], pl0['countP'], 'go', color='g')
# pl.plot(pl0['x'], pl0['y'], 'go', markersize=3)

# pl.show()

test['pred'] = result.predict(test[train_cols])

# pl.plot(test['countP'], test['pred'], 'ro',  markersize=2)
# pl.plot(test.index.values, test['height']/300, color='g')
# pl.show()

counter = 0

for index, row in test.iterrows():
    if round(row['pred']) == row['arch']:
        counter += 1

print counter, len(test), str(counter / float(len(test)))
