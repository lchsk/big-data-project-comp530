import math
import random
import operator

points = {}
arealist = []
results = []

positive = []
negative = []
final = []

def dist(x1, y1, x2, y2):
    return math.sqrt(math.pow(x1-x2, 2) + math.pow(y1-y2, 2))

def get_point():
    return random.randint(10000, 89999)

def get_areas():
    f = open('clean.csv')

    _areas = []

    for line in f:
        line = line.replace('"', '')
        positive.append('1,' + line)
        d = line.split(',')

        if d[1] not in _areas:
            _areas.append(d[1])
    f.close()

    return _areas

def find_max_dist(x, y):

    _max = 0
    _sum = 0

    # for line in arealist:
    #     _x = int(line[2])
    #     _y = int(line[3])
    #
    #     d = dist(x, _x, y, _y)
    #
    #     if d > _max:
    #         _max = d

    # compute average distances to every known point
    for line in arealist:
        _x = int(line[2])
        _y = int(line[3])

        d = dist(x, _x, y, _y)

        if d < 10000:
            _sum += d

    return _sum

def count_points_radius(x, y, radius, arealist):

    _sum = 0

    for line in arealist:
        _x = int(line[2])
        _y = int(line[3])

        d = dist(x, _x, y, _y)

        if d < radius:
            _sum += 1

    return _sum

def get_points_from_area(area):

    _arealist = []

    for line in positive:
        line = line.replace('"', '')
        d = line.split(',')
        if d[1] == area:
            _arealist.append(d)

    return _arealist

def inputf(area):
    f = open('clean.csv')

    for line in f:
        line = line.replace('"', '')
        # print line
        d = line.split(',')

        if d[1] == area:
            arealist.append(d)
    f.close()

def add_results(n, results, area):

    for line in results[:n]:
        negative.append('0,666,' + area + ',' + str(line[0][0]) + ',' + str(line[0][1]) + ',1,1,0,0\n')

def save(n):

    global results

    f = open('neg.csv', 'w')
    f.write('arch,x,y\n')

    for line in results[:n]:
        f.write('0' + ',' + str(line[0][0] / 100000.0) + ',' + str(line[0][1] / 100000.0) + '\n')

    f.close()

def save_all():

    f = open('all.csv', 'w')

    for line in final:
        f.write(line)

    f.close()

def select_points(n, arealist):

    results = []

    for i in xrange(0, n):
        x = get_point()
        y = get_point()
        # d = find_max_dist(x, y)

        # find how many points are within given radius
        points_radius = count_points_radius(x, y, 2000, arealist)

        points[(x, y)] = points_radius

    # print points

    # results = sorted(points.items(), key=operator.itemgetter(1), reverse=True)
    results = sorted(points.items(), key=operator.itemgetter(1), reverse=False)

    return results

if __name__ == '__main__':

    areas = get_areas()

    for area in areas:
        print 'Area: {0}'.format(area)
        _arealist = get_points_from_area(area)
        results = select_points(20000, _arealist)
        add_results(500, results, area)

    final = positive + negative
    random.shuffle(final)
    save_all()

    # inputf('SU')

    # select_points(20000)

    # save(500)
