package java_itamae_contents.domain.service.contents;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import java_itamae_contents.domain.model.ContentsAttribute;
import java_itamae_contents.domain.repository.contents.ContentsRepository;
import java_itamae_contents.domain.repository.contents.ContentsRepositoryImpl;
import java_itamae_contents.domain.repository.stream.StreamRepository;
import java_itamae_contents.domain.repository.stream.StreamRepositoryImpl;

public class ContentsServiceImpl implements ContentsService {
    private final ContentsAttribute attr;
    private final StreamRepository sr;
    private final ContentsRepository cr;

    public ContentsServiceImpl(ContentsAttribute attr) {
        this.attr = attr;
        sr = new StreamRepositoryImpl();
        cr = new ContentsRepositoryImpl();
    }

    @Override
    public List<String> getContents() throws Exception {
        List<String> contents = new ArrayList<String>();

        try (Reader reader = sr.getReader(attr)) {
            contents = cr.getContents(reader);
        }

        return contents;
    }

    @Override
    public boolean setContent(String content) throws Exception {
        boolean status = false;
        final File file = new File(attr.getPath());

        if (!file.isFile()) {
            file.createNewFile();
        }

        final List<String> contents = new ArrayList<String>();
        contents.add(content);

        try (Writer writer = sr.getWriter(attr)) {
            status = cr.setContents(writer, contents);
        }

        return status;
    }

    @Override
    public boolean appendContent(String content) throws Exception {
        boolean status = false;
        final File file = new File(attr.getPath());

        if (!file.isFile()) {
            file.createNewFile();
        }

        final List<String> contents = getContents();
        contents.add(content);

        try (Writer writer = sr.getWriter(attr)) {
            status = cr.setContents(writer, contents);
        }

        return status;
    }

    @Override
    public boolean deleteContents() throws Exception {
        boolean status = false;

        final List<String> contents = getContents();

        if (contents.size() > 0) {
            try (Writer writer = sr.getWriter(attr)) {
                status = cr.deleteContents(writer);
            }
        }

        return status;
    }
}
