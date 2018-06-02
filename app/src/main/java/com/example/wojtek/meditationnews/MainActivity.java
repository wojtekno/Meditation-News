package com.example.wojtek.meditationnews;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsObject>> {

    private final String JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":6900,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":690,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"lifeandstyle/2018/may/12/fabric-swaps-hedonism-meditation-mindfulness-nightclub\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2018-05-12T13:00:31Z\",\"webTitle\":\"Fabric swaps hedonism for meditation\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2018/may/12/fabric-swaps-hedonism-meditation-mindfulness-nightclub\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2018/may/12/fabric-swaps-hedonism-meditation-mindfulness-nightclub\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"world/2018/mar/29/british-woman-goes-missing-from-brazil-meditation-retreat\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-03-29T19:04:23Z\",\"webTitle\":\"British woman goes missing from Brazil meditation retreat\",\"webUrl\":\"https://www.theguardian.com/world/2018/mar/29/british-woman-goes-missing-from-brazil-meditation-retreat\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/mar/29/british-woman-goes-missing-from-brazil-meditation-retreat\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"lifeandstyle/2018/mar/23/chill-out-meditators-annoying-oliver-burkeman\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2018-03-23T15:00:06Z\",\"webTitle\":\"Do meditators annoy you? Try meditating | Oliver Burkeman\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2018/mar/23/chill-out-meditators-annoying-oliver-burkeman\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2018/mar/23/chill-out-meditators-annoying-oliver-burkeman\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"lifeandstyle/2017/oct/20/meditation-on-a-lack-of-conservative-morality\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2017-10-20T17:34:43Z\",\"webTitle\":\"Meditation on a lack of Conservative morality | Letters\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2017/oct/20/meditation-on-a-lack-of-conservative-morality\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2017/oct/20/meditation-on-a-lack-of-conservative-morality\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"tv-and-radio/2018/jan/04/black-mirrors-meditation-on-star-trek-reinforcing-trekker-stereotypes\",\"type\":\"article\",\"sectionId\":\"tv-and-radio\",\"sectionName\":\"Television & radio\",\"webPublicationDate\":\"2018-01-04T08:00:08Z\",\"webTitle\":\"Black Mirror's meditation on Star Trek: reinforcing Trekker stereotypes?\",\"webUrl\":\"https://www.theguardian.com/tv-and-radio/2018/jan/04/black-mirrors-meditation-on-star-trek-reinforcing-trekker-stereotypes\",\"apiUrl\":\"https://content.guardianapis.com/tv-and-radio/2018/jan/04/black-mirrors-meditation-on-star-trek-reinforcing-trekker-stereotypes\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"stage/2017/sep/24/deadclub-david-rosenberg-frauke-requardt-the-place-review\",\"type\":\"article\",\"sectionId\":\"stage\",\"sectionName\":\"Stage\",\"webPublicationDate\":\"2017-09-24T06:59:38Z\",\"webTitle\":\"DeadClub review – a Lynchian meditation on mortality\",\"webUrl\":\"https://www.theguardian.com/stage/2017/sep/24/deadclub-david-rosenberg-frauke-requardt-the-place-review\",\"apiUrl\":\"https://content.guardianapis.com/stage/2017/sep/24/deadclub-david-rosenberg-frauke-requardt-the-place-review\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"books/2018/feb/17/dont-skip-out-on-me-willy-vlautin-review\",\"type\":\"article\",\"sectionId\":\"books\",\"sectionName\":\"Books\",\"webPublicationDate\":\"2018-02-17T07:30:09Z\",\"webTitle\":\"Don’t Skip Out on Me by Willy Vlautin review – a meditation on loneliness\",\"webUrl\":\"https://www.theguardian.com/books/2018/feb/17/dont-skip-out-on-me-willy-vlautin-review\",\"apiUrl\":\"https://content.guardianapis.com/books/2018/feb/17/dont-skip-out-on-me-willy-vlautin-review\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"lifeandstyle/2018/feb/10/transcendental-meditation-guru-bob-roth-book-uk\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2018-02-10T22:15:00Z\",\"webTitle\":\"Top US meditation teacher brings his message to stressed-out Britons\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2018/feb/10/transcendental-meditation-guru-bob-roth-book-uk\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2018/feb/10/transcendental-meditation-guru-bob-roth-book-uk\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"film/2017/oct/05/the-ornithologist-review-anthony-of-padua-joao-pedro-rodrigues\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2017-10-05T05:00:15Z\",\"webTitle\":\"The Ornithologist review – beautiful, erotic and baffling meditation on faith\",\"webUrl\":\"https://www.theguardian.com/film/2017/oct/05/the-ornithologist-review-anthony-of-padua-joao-pedro-rodrigues\",\"apiUrl\":\"https://content.guardianapis.com/film/2017/oct/05/the-ornithologist-review-anthony-of-padua-joao-pedro-rodrigues\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"music/2017/sep/21/moses-sumney-aromanticism-review\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-09-21T20:45:28Z\",\"webTitle\":\"Moses Sumney: Aromanticism review – warmly absorbing meditation on lovelessness\",\"webUrl\":\"https://www.theguardian.com/music/2017/sep/21/moses-sumney-aromanticism-review\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/sep/21/moses-sumney-aromanticism-review\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"}]}}";
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private final String jsonRequestString = "https://content.guardianapis.com/search?q=meditation&api-key=test";
    List<NewsObject> newsObjectList;
    private NewsAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);

        arrayAdapter = new NewsAdapter(this, 0, new ArrayList<NewsObject>());
        listView.setAdapter(arrayAdapter);

        LoaderManager loaderManager = getLoaderManager();
        Log.e(LOG_TAG,"loaderManager.initLoader" );
        loaderManager.initLoader(0, null, this );

//        newsObjectList = QueryUtils.extractNewsFromData(JSON_RESPONSE);
//        arrayAdapter.addAll(newsObjectList);
    }


    @Override
    public Loader<List<NewsObject>> onCreateLoader(int id, Bundle args) {
        Log.e(LOG_TAG,"onCreateLoader");
        return new NewsLoader(this, jsonRequestString);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsObject>> loader, List<NewsObject> data) {
        Log.e(LOG_TAG,"onLoadFinished");
        arrayAdapter.clear();
        arrayAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsObject>> loader) {
        Log.e(LOG_TAG,"onLoadReset");
        arrayAdapter.clear();
    }
}
