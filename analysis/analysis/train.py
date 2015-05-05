import pandas as pd
import statsmodels.api as sm
import numpy as np
import matplotlib.pyplot as pl

class TrainingEvaluation(object):
    def __init__(self):
        self.data = pd.read_csv("train.csv")
        self.data.columns = ["arch", "height", "distM", "distP", "distB", "countM", 'countP', 'countB', 'e','n','lat','lon']
        self.data['intercept'] = 1.0
        self.train_cols = ['height', 'countP', 'distB', 'distP', 'distM']

    def evaluate(self):
        self.train = self.data[:400]
        self.test = self.data[400:]

        self.poisson()
        print '\n' + '*' * 50 + '\n'
        self.probit()
        print '\n' + '*' * 50 + '\n'
        self.ols()
        print '\n' + '*' * 50 + '\n'
        self.negbinomial()
        print '\n' + '*' * 50 + '\n'
        self.logistic()



        # pl1 = train.ix[train['arch'] == 1]
        # pl0 = train.ix[train['arch'] == 0]

    def get_results(self):
        result = self.model.fit()
        self.test['pred'] = result.predict(self.test[self.train_cols])

        print result.summary()
        self.score()

    def logistic(self):
        self.model = sm.Logit(self.train['arch'], self.train[self.train_cols])
        self.get_results()

    def poisson(self):
        self.model = sm.Poisson(self.train['arch'], self.train[self.train_cols])
        self.get_results()

    def probit(self):
        self.model = sm.Probit(self.train['arch'], self.train[self.train_cols])
        self.get_results()

    def negbinomial(self):
        self.model = sm.NegativeBinomial(self.train['arch'], self.train[self.train_cols])
        self.get_results()

    def ols(self):
        self.model = sm.OLS(self.train['arch'], self.train[self.train_cols])
        self.get_results()

    def score(self):
        tp = tn = fp = fn = 0

        for index, row in self.test.iterrows():
            if round(row['pred']) == row['arch'] == 1:
                tp += 1
            if round(row['pred']) == row['arch'] == 0:
                tn += 1

            if (round(row['pred']) == 1) and (row['arch'] == 0):
                fp += 1
            if (round(row['pred']) == 0) and (row['arch'] == 1):
                fn += 1


        print 'Correctly identified: {0}/{1} ({2} %)'.format(tp + tn, len(self.test), round((tp + tn) / float(len(self.test)), 3))
        print 'True positives: {0}/{1} ({2} %)'.format(tp, len(self.test), round(tp / float(len(self.test)), 3))
        print 'True negatives: {0}/{1} ({2} %)'.format(tn, len(self.test), round(tn / float(len(self.test)), 3))
        print 'False positives: {0}/{1} ({2} %)'.format(fp, len(self.test), round(fp / float(len(self.test)), 3))

        print 'False negatives: {0}/{1} ({2} %)'.format(fn, len(self.test), round(fn / float(len(self.test)), 3))

# test = pd.read_csv('data/test_SU.csv')

# print result.conf_int()

# Height/mark correlation
# pl.plot(pl1['distP'], pl1['countP'], 'ro', color='r')
# pl.plot(pl0['distP'], pl0['countP'], 'go', color='g')
# pl.plot(pl0['x'], pl0['y'], 'go', markersize=3)

# pl.show()

# test['pred'] = result.predict(test[train_cols])
#
# print test.head()
#
# test.hist()
#
# pl.plot(test['distM'], test['pred'], 'ro',  markersize=5)
# # pl.plot(test.index.values, test['height'], 'ro', color='g')
# pl.show()
#
#
#
