<head>
        <title>目次</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="../../resources/css/default.css"></link>
</head>
<body>
    <div class="title">
        <h1>テンプレートテストクラスの実装</h1>
        <p>
            テンプレートテストクラスの実装方法を明記する。ここではすべてのテストクラスの共通処理をコーディングする。ここに記載するコードが多くなるようにテスト設計をすることで、テストスクリプトのコーディングを大幅に減らすことができる。そして保守作業も容易になる。
        </p>
    </div>
    <div class="index">
        <ol type="1">
            <li>コンストラクタの実装</li>
            <li>イニシャライザーの実装</li>
            <li>beforeAllの実装</li>
            <li>beforeEachの実装</li>
            <li>afterEachの実装</li>
            <li>afterAllの実装</li>
        </ol>
    </div>
    <div class="howTo">
        <h2>コンストラクタの実装</h2>
        <p>
            すべてのテストクラスで実施するコンストラクターとしての処理を実装する。<br>
            デフォルトではObserveクラスの設定を行っている。
        </p>
    </div>
    <div class="howTo">
        <h2>イニシャライザーの実装</h2>
        <p>
            すべてのテストクラスで実施するイニシャライザーとしての処理を実装する。<br>
            デフォルトでは処理なし。
        </p>
    </div>
    <div class="howTo">
        <h2>beforeAllの実装</h2>
        <p>
            すべてのテストクラスでテスト打鍵前に一度だけ実施する共通処理を実装する。<br>
            デフォルトでは処理なし。
        </p>
    </div>
    <div class="howTo">
        <h2>beforeEachの実装</h2>
        <p>
            すべてのテストクラスでテスト打鍵前に毎回実施する共通処理を実装する。<br>
            デフォルトでは次に打鍵するテストケースの情報を設定し、Obsererクラスにテスト打鍵開始の通知をする処理を実装している。
            <pre><code>
    public void beforeEach(TestInfo testInfo) throws Exception { <br>
        setTestInfo(testInfo); <br>
        notifyObservers(); <br>
        // TODO: 汎用性のある事前共通処理を書く <br>
    }
            </code></pre>
        </p>
    </div>
    <div class="howTo">
        <h2>afterEachの実装</h2>
        <p>
            すべてのテストクラスでテスト打鍵後に毎回実施する共通処理を実装する。<br>
            Observerクラスにテストが終了したことを通知する処理を実装している。
        </p>
    </div>
    <div class="howTo">
        <h2>afterAllの実装</h2>
        <p>
            すべてのテストクラスでテスト打鍵後に最後に実施する共通処理を実装する。<br>
            デフォルトでは処理なし。
        </p>
    </div>
    <div>
        <a href="tuningScreenshot.md">戻る</a>
        <a href="implementPageobjectClass.md">次へ</a>
        <a href="../index.md">目次に戻る</a>
    </div>
</body>