package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final String PROGRAM_NAME = "filedump";
    private static final String PROGRAM_VERSION = "-1-0-0";

    private static final String PROGRAM_CMD = "java -jar";
    private static final String PROGRAM_SUFFIX = "-SNAPSHOT-jar-with-dependencies.jar";

    private static final String SEPARATOR = " ";

    private static final String OPTION_ARGUMENTS_HELP = "--help";
    private static final String OPTION_DIRECTORY_NAME = "/home/aine/wrksp";

    private static final String DEFAULT_STRING_NONE_VALUE = "";
    private static final Boolean DEFAULT_BOOLEAN_NONE_VALUE = false;
    private static final Long DEFAULT_LONG_NONE_VALUE = 0L;

    private static final String FILE_FULL_PATH_NAME = "fileFullPathName";
    private static final String IS_OTHER = "isOther";
    private static final String IS_DIRECTORY = "isDirectory";
    private static final String IS_REGULAR_FILE = "isRegularFile";
    private static final String IS_SYMBOLIC_LINK = "isSymbolicLink";
    private static final String CREATION_TIME = "creationTime";
    private static final String LAST_MODIFIED_TIME = "lastModifiedTime";
    private static final String LAST_ACCESS_TIME = "lastAccessTime";
    private static final String SIZE = "size";
    private static final String OWNER = "owner";
    private static final String GROUP = "group";
    private static final String PERMISSION = "permission";

    private static final List<String> OUTPUT_HEADER_LIST = new LinkedList(){{
        add(FILE_FULL_PATH_NAME);
        add(IS_DIRECTORY);
        add(IS_REGULAR_FILE);
        add(IS_SYMBOLIC_LINK);
        add(IS_OTHER);
        add(CREATION_TIME);
        add(LAST_MODIFIED_TIME);
        add(LAST_ACCESS_TIME);
        add(SIZE);
        add(OWNER);
        add(GROUP);
        add(PERMISSION);
    }};

    private static void Usage(){
        System.out.println("Usageだよーん" +
                RS +
                RS +
                PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX + SEPARATOR + OPTION_DIRECTORY_NAME +
                RS +
                RS +
                ""
        );
        System.exit(0);
    }

    public static void main(String... cmdLineArgs) throws IOException {

        List<String> cmdLineList = new LinkedList<>(Arrays.asList(cmdLineArgs));

        if(cmdLineList.size()!=1){
            Usage();
        }

        String targetDirName = null;

        for(String arg : cmdLineList){
            switch (arg){
                case OPTION_ARGUMENTS_HELP:
                    Usage();
                    break;
                default:

                    File file = new File(arg);

                    if(! file.isDirectory()){
                        Usage();
                    }

                    targetDirName = arg;

                    break;
            }
        }

        Path targetDir = Paths.get(targetDirName);

        List<Path> pathList = Files.walk(targetDir).collect(Collectors.toList());

        List<Map<String,String>> summaryList = new LinkedList();

        for(Path path : pathList){

            Map<String,String> detailMap = new LinkedHashMap<>();

            BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            PosixFileAttributes posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);

            detailMap.put(FILE_FULL_PATH_NAME,path.toFile().getAbsolutePath());
            detailMap.put(IS_OTHER,String.valueOf(Optional.ofNullable(basicFileAttributes.isOther()).orElse(DEFAULT_BOOLEAN_NONE_VALUE)));
            detailMap.put(IS_DIRECTORY,String.valueOf(Optional.ofNullable(basicFileAttributes.isDirectory()).orElse(DEFAULT_BOOLEAN_NONE_VALUE)));
            detailMap.put(IS_REGULAR_FILE,String.valueOf(Optional.ofNullable(basicFileAttributes.isRegularFile()).orElse(DEFAULT_BOOLEAN_NONE_VALUE)));
            detailMap.put(IS_SYMBOLIC_LINK,String.valueOf(Optional.ofNullable(basicFileAttributes.isSymbolicLink()).orElse(DEFAULT_BOOLEAN_NONE_VALUE)));
            detailMap.put(CREATION_TIME,String.valueOf(Optional.ofNullable(basicFileAttributes.creationTime().toString()).orElse(DEFAULT_STRING_NONE_VALUE)));
            detailMap.put(LAST_MODIFIED_TIME,String.valueOf(Optional.ofNullable(basicFileAttributes.lastAccessTime().toString()).orElse(DEFAULT_STRING_NONE_VALUE)));
            detailMap.put(LAST_ACCESS_TIME,String.valueOf(Optional.ofNullable(basicFileAttributes.lastModifiedTime().toString()).orElse(DEFAULT_STRING_NONE_VALUE)));

            detailMap.put(SIZE,String.valueOf(Optional.ofNullable(basicFileAttributes.size()).orElse(DEFAULT_LONG_NONE_VALUE)));
            detailMap.put(OWNER,String.valueOf(Optional.ofNullable(posixFileAttributes.owner().getName()).orElse(DEFAULT_STRING_NONE_VALUE)));
            detailMap.put(GROUP,String.valueOf(Optional.ofNullable(posixFileAttributes.group().getName()).orElse(DEFAULT_STRING_NONE_VALUE)));
            detailMap.put(PERMISSION,String.valueOf(Optional.ofNullable(PosixFilePermissions.toString(posixFileAttributes.permissions())).orElse(DEFAULT_STRING_NONE_VALUE)));

            summaryList.add(detailMap);
        }

        if(summaryList.size() > 0){

            System.out.println(OUTPUT_HEADER_LIST.stream().collect(Collectors.joining(FS)));

        }

        for(Map<String,String> detailMap : summaryList){
            {
                System.out.print(detailMap.get(FILE_FULL_PATH_NAME));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_DIRECTORY));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_REGULAR_FILE));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_SYMBOLIC_LINK));
                System.out.print(FS);
                System.out.print(detailMap.get(IS_OTHER));
                System.out.print(FS);
                System.out.print(detailMap.get(CREATION_TIME));
                System.out.print(FS);
                System.out.print(detailMap.get(LAST_MODIFIED_TIME));
                System.out.print(FS);
                System.out.print(detailMap.get(LAST_ACCESS_TIME));
                System.out.print(FS);
                System.out.print(detailMap.get(SIZE));
                System.out.print(FS);
                System.out.print(detailMap.get(OWNER));
                System.out.print(FS);
                System.out.print(detailMap.get(GROUP));
                System.out.print(FS);
                System.out.print(detailMap.get(PERMISSION));
            }
            System.out.println();
        }
    }
}
