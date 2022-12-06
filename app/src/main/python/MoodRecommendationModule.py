from os.path import dirname,join

import pandas as pd
from sklearn.cluster import KMeans
from sklearn.preprocessing import MinMaxScaler

filename = join(dirname(__file__),"data.csv")

data = pd.read_csv(filename,index_col = 0)
name=data['song_title']

col_features = ['danceability', 'energy', 'valence', 'loudness']
X = MinMaxScaler().fit_transform(data[col_features])
kmeans = KMeans(init="k-means++",
                n_clusters=2,
                random_state=15).fit(X)
data['kmeans'] = kmeans.labels_

data['song_title']=name

data

cluster=data.groupby(by=data['kmeans'])

y=data.pop('kmeans')
x=data.drop(columns=['song_title','artist'])

from sklearn.model_selection import train_test_split
x_train,x_test,y_train,y_test=train_test_split(x,y,test_size=0.25)

# from lightgbm import LGBMClassifier
from sklearn.neighbors import KNeighborsClassifier
knn = KNeighborsClassifier(n_neighbors = 5)
model=knn.fit(x_train,y_train)

model.score(x_train,y_train)

model.score(x_test,y_test)

df=cluster.apply(lambda x: x.sort_values(["time_signature"],ascending=False))
df.reset_index(level=0, inplace=True)

def get_results(emotion):
    NUM_RECOMMEND=1
    happy_set=[]
    sad_set=[]
    if emotion =='Sad':
        # happy_set.append(df[df['kmeans']==0]['song_title'].head(NUM_RECOMMEND))
        # return happy_set
        return "Listen Music : " + df[df['kmeans']==0]['song_title'].head(NUM_RECOMMEND).to_string(index = False)
    elif emotion == 'Happy':
        sad_set.append(df[df['kmeans']==1]['song_title'].head(NUM_RECOMMEND))
        return "Listen Music : " + df[df['kmeans']==1]['song_title'].head(NUM_RECOMMEND).to_string(index = False)


####################################################################################################################
# from faker import Faker
#
# def recommend_date():
#     fake = Faker()
#
#     recomm_time = fake.date_between(start_date='today', end_date='+1M')
#     return recomm_time.strftime('%d-%m-%Y')
from faker import Faker
fake = Faker()

recomm_time = fake.date_between(start_date='today', end_date='+1M')
# day = recomm_time.strftime('%d')
# month = recomm_time.strftime('%m')
# year = recomm_time.strftime('%Y')

def recomm_day():
    day = recomm_time.strftime('%d')
    return day

def recomm_month():
    month = recomm_time.strftime('%m')
    return month

def recomm_year():
    year = recomm_time.strftime('%Y')
    return year