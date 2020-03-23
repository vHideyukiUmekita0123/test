<head>
        <title>目次</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="../../resources/css/default.css"></link>
</head>
<body>
    <div class="title">
        <h1>スクリーンショット処理のチューニング</h1>
        <p>
            テスト対象のアプリケーションに合わせてスクリーンショット処理をチューニングする必要がある。ここではチューニング方法を明記する。
        </p>
    </div>
    <div class="index">
        <ol type="1">
            <li>スクロールバーの幅を設定</li>
            <li>ドロップシャドウの幅を設定</li>
            <li>拡張子の設定</li>
            <li>ホーミングするヘッダーエリア対応</li>
        </ol>
    </div>
    <div class="howTo">
        <h2>スクロールバーの幅を設定</h2>
        <p>
            キー<code>SCROLL_BAR_WIDTH</code>にスクロールバーの幅を設定する。<br>
            デフォルトは<code>18px</code>。
        </p>
        <pre><code>
    private static final int SCROLL_BAR_WIDTH = 18;
        </code></pre>
    </div>
    <div>
        <h2>ドロップシャドウの幅を設定</h2>
        <p>
            キー<code>DROP_SHADOW_WIDTH</code>にドロップシャドウの幅を設定する。<br>
            デフォルトは<code>10px</code>。
        </p>
        <pre><code>
    private static final int DROP_SHADOW_WIDTH = 10;
        </code></pre>
    </div>
    <div>
        <h2>拡張子の設定</h2>
        <p>
            キー<code>EXTENSION</code>にスクリーンショットのファイル形式を設定する。<br>
            デフォルトは<code>.jpg</code>。
        </p>
        <pre><code>
    private static final String EXTENSION = ".jpg";
        </code></pre>
    </div>
    <div>
        <h2>ホーミングするヘッダーエリア対応</h2>
        <p>
            最近のイケてるWebページにはスクロールすると一緒についてくるヘッダーエリアがあります。<br>
            デフォルトでは下記処理で実現しています。<br>
        <pre><code>
        // スクロールするときにHeaderAreaがついてくるのでその分をImageに描写しないようにする <br>
        try { <br>
            HeaderArea headerArea = new HeaderArea(driver); <br>
            headerAreaHeight  = headerArea.getHeaderAreaHeight() + DROP_SHADOW_WIDTH; <br>
        } catch (IllegalPageException e) { <br>
            headerAreaHeight = 0; <br>
        } <br>
        </code></pre>
        もし必要がない場合は上記部分を下記に修正してください。
        <pre><code>
        headerAreaHeight = 0; <br>
        </code></pre>
        </p>
    </div>
    <div>
        <a href="setDatabase.md">戻る</a>
        <a href="implementTemplateTestClass.md">次へ</a>
        <a href="../index.md">目次に戻る</a>
    </div>
</body>