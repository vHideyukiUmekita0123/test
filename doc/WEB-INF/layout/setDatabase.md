<body>
    <div>
        <h1>接続データベースの設定</h1>
        <p>テスト対象のアプリケーションがデータベースの接続情報の設定方法を明記する。想定している状況として「テスト実行中にデータベースのレコードを参照したい」など。</p>
    </div>
    <div class="index">
        <ol type="1">
            <li>database.propertiesを開く</li>
            <li>データベースサーバーのURLを設定</li>
            <li>データベースのユーザーIDを設定</li>
            <li>データベースのログインパスワードを設定</li>
        </ol>
    </div>
    <div class="howTo">
        <h2>database.propertiesを開く</h2>
        <p>
            <code>src/main/resources/database.properties</code>を開く。
        </p>
    </div>
    <div class="howTo">
        <h2>データベースサーバーのURLを設定</h2>
        <p>
            キー<code></code>に接続するデータベースのURLを記述する。<br>
            基本お客様にご連携頂く必要がある。<br>
            例：
            <pre>
                url=http://host:port
            </pre>
        </p>
    </div>
    <div class="howTo">
        <h2>データベースのユーザーIDを設定</h2>
        <p>
            キー<code>userId</code>に接続するデータベースのログインIDを記述する。<br>
            基本お客様にご連携頂く必要がある。<br>
            例：
            <pre>
                userId=root
            </pre>
        </p>
    <div>
    <div class="howTo">
        <h2>データベースのログインパスワードを設定</h2>
        <p>
            キー<code>password</code>に、上記ユーザーIDに紐づくログインパスワードを記述する。<br>
            基本お客様にご連携頂く必要がある。<br>
        </p>
    </div>
    <div>
        <h2>※キー test について</h2>
        <p>
            キー<code>test</code>はフレームワークのテストで使用するキー。そのため変更不要。
        </p>
    </div>
    <div>
        <a href="setTestEnvironment.md">戻る</a>
        <a href="tuningScreenshot.md">次へ</a>
        <a href="../index.md">目次に戻る</a>
    </div>
</body>