<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="../../resources/css/default.css"></link>
</head>
<body>
    <div>
        <h1>テスト環境の設定</h1>
        <p>テスト対象のアプリケーションをフレームワークに設定する方法を明記する。</P>
    </div>
    <div class="index">
        <ol type="1">
            <li>settings.propertiesを開く</li>
            <li>ChromeDriverのパスの設定</li>
            <li>Chrome拡張機能の設定</li>
            <li>Chromeのインフォバー表示フラグの設定</li>
            <li>Chromeのpopup制御フラグの設定</li>
            <li>ブラウザウィンドウの横幅の設定</li>
            <li>ブラウザウィンドウの縦幅の設定</li>
            <li>ブラウザの垂直方向の基準点の設定</li>
            <li>ブラウザの水平方向の基準点の設定</li>
            <li>エビデンスを保存する親ディレクトリ名の設定</li>
        </ol>
    </div>
    <div class="howTo">
        <h2>settings.propertiesを開く</h2>
        <p>
            <code>src/main/resources/settings.properties</code>を開く。
        </p>
    </div>
    <div class="howTo">
        <h2>ChromeDriverのパスの設定</h2>
        <p>
            キー<code>webDriverPath</code>に使用するバージョンのChromeDriver.exeの配置パスを記述する。<br>
            <code>src/main/resources/driver</code>配下に配置する場合は<code>src</code>からの相対パスをキー値に設定する。<br>
            それ以外に配置する場合はキー値に配置ディレクトリまでの絶対パスを設定する。
        </p>
    </div>
    <div class="howTo">
        <h2>Chrome拡張機能の設定</h2>
        <p>
            キー<code>useExtension</code>にChrome拡張機能を使用する場合は<code>true</code>を設定する。<br>
            当フレームワークでは現状Chrome拡張機能を使用することを想定していない。そのためデフォルトは<code>false</code>になっている。
        </p>
    </div>
    <div class="howTo">
        <h2>Chromeのインフォバー表示フラグの設定</h2>
        <p>
            「自動テストソフトソフトウェアによって制御されています」のインフォバーの表示非表示を設定する。<br>
            キー<code>displayInfoBar</code>に<code>true</code>を設定すると上記インフォバーが表示される。<br>
            当フレームワークでは現状インフォバーを表示させることを想定していない。そのためデフォルトは<code>false</code>になっている。
        </p>
    </div>
    <div class="howTo">
        <h2>Chromeのpopup制御フラグの設定</h2>
        <p>
            Chromeのポップアップをブロックまたは許可するかのを設定する。<br>
            キー<code>controlPopup</code>に<code>1</code>を設定するとポップアップをブロックする。<br>
            当フレームワークでは現状ポップアップをブロックすることを想定していない。そのためデフォルトは<code>0</code>になっている。
        </p>
    </div>
    <div class="howTo">
        <h2>ブラウザウィンドウの横幅の設定</h2>
        <p>
            キー<code>windowWidth</code>にブラウザのウィンドウの横幅を設定する。<br>
            当フレームワークのデフォルトは<code>1280</code>pixelになっている。
        </p>
    </div>
    <div class="howTo">
        <h2>ブラウザウィンドウの縦幅の設定</h2>
        <p>
            キー<code>windowHight</code>にブラウザのウィンドウの縦幅を設定する。<br>
            当フレームワークのデフォルトは<code>768</code>pixelになっている。
        </p>
    </div>
    <div class="howTo">
        <h2>ブラウザの垂直方向の基準点の設定</h2>
        <p>
            キー<code>verticalPosition</code>にブラウザのウィンドウの垂直方向の基準点を設定する。<br>
            当フレームワークのデフォルトは<code>0</code>（ウィンドウの左上）になっている。
        </p>
    </div>
    <div class="howTo">
        <h2>ブラウザの水平方向の基準点の設定</h2>
        <p>
            キー<code>horizontalPosition</code>にブラウザのウィンドウの水平方向の基準点を設定する。<br>
            当フレームワークのデフォルトは<code>0</code>（ウィンドウの左上）になっている。
        </p>
    </div>
    <div class="howTo">
        <h2>エビデンスを保存する親ディレクトリ名の設定</h2>
        <p>
            キー<code>evidencePath</code>にエビデンスを保存する親ディレクトリ名の設定する。<br>
            当フレームワークのデフォルトは<code>selenium-java-framework/evidence</code>配下にエビデンスが保存される。
        </p>
    </div>
    <div>
        <h2>※キー test について</h2>
        <p>
            キー<code>test</code>はフレームワークのテストで使用するキー。そのため変更不要。
        </p>
    </div>
    <div>
        <a href="downloadSource.md">戻る</a>
        <a href="setDatabase.md">次へ</a>
        <a href="../index.md">目次に戻る</a>
    </div>
</body>