package java_itamae_contents.domain.repository.contents;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

public interface ContentsRepository {
    /**
     * テキストファイルからの読込みを実行する。
     *
     * @param reader 操作対象とするテキストファイルの Reader を指定する。
     * @return contents テキストファイルを読込み、 List に変換して返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public List<String> getContents(Reader reader) throws Exception;

    /**
     * テキストファイルへの書込みを実行する。
     *
     * @param writer   操作対象とするテキストファイルの Writer を指定する。
     * @param contents 書込み対象とする List を指定する。
     * @return status
     *         <ul>
     *         <li>true: 書込みに成功したことを表す。</li>
     *         <li>false: 書込みをしなかったことを表す。</li>
     *         </ul>
     * @throws Exception {@link java.lang.Exception}
     */
    public boolean setContents(Writer writer, List<String> contents) throws Exception;

    /**
     * テキストファイルの内容を空にする。
     *
     * @param writer 操作対象とするテキストファイルの Writer を指定する。
     * @return status
     *         <ul>
     *         <li>true: 削除に成功したことを表す。</li>
     *         <li>false: 削除をしなかったことを表す。</li>
     *         </ul>
     * @throws Exception {@link java.lang.Exception}
     */
    public boolean deleteContents(Writer writer) throws Exception;
}
