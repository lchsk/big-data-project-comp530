import pandas as pd
import statsmodels.api as sm
import numpy as np
import matplotlib.pyplot as pl

# posTQ - error bottom left
# TRAIN_ - SU cleaned with 0s

df = pd.read_csv("TRAIN_.csv")
# df2 = pd.read_csv("neg.csv")

df.columns = ["arch", "x", "y"]
# df2.columns = ["arch", "x", "y"]

# print df.describe()

# print df['arch']

pl1 = df.ix[df['arch'] == 1]
pl0 = df.ix[df['arch'] == 0]

# pl.plot(df2['x'], df2['y'], 'go', markersize=3)
pl.plot(pl1['x'], pl1['y'], 'ro', markersize=3)
pl.plot(pl0['x'], pl0['y'], 'go', markersize=3)


pl.show()
