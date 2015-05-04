

f = open('cleaned_WholeUK.csv')
f2 = open('clean.csv', 'w')

c = 0

for line in f:
    d = line.split(',')

    if d[0] == "\"\"": continue

    if int(d[2]) < 10000 and int(d[3]) < 10000:
        c += 1
    else:
        f2.write(','.join(d))

print c

f.close()
f2.close()
