package me.hottutorials.content.modrinth;

import me.hottutorials.content.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModrinthFilter implements Filter {

    private final List<ModrinthCategories> categories;
    private final List<String> versions;
    private final String license;
    private final String clientSide;
    private final String serverSide;

    public ModrinthFilter(List<ModrinthCategories> categories, List<String> versions, String license, String clientSide, String serverSide) {
        this.categories = categories;
        this.versions = versions;
        this.license = license;
        this.clientSide = clientSide;
        this.serverSide = serverSide;
    }

    public String getQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("filters=");

        // Category Adder
        for (ModrinthCategories category : categories) builder.append("categories=\"").append(category.name().toLowerCase()).append("\"").append("AND");

        // Version Adder
        for (String version : versions) builder.append("versions=\"").append(version.toLowerCase()).append("\"AND");

        if(license != null) builder.append("license=\"").append(license).append("\"AND");
        if(clientSide != null && (clientSide.equals("required") || clientSide.equals("unsupported"))) builder.append("client_side=\"").append(clientSide).append("\"AND");
        if(serverSide != null && (serverSide.equals("required") || serverSide.equals("unsupported"))) builder.append("server_side=\"").append(serverSide).append("\"AND");

        return builder.substring(0, builder.toString().length() - 3);

    }

    public List<ModrinthCategories> getCategories() { return categories; }
    public String getClientSide() { return clientSide; }
    public String getServerSide() { return serverSide; }
    public String getLicense() { return license; }
    public List<String> getVersions() { return versions; }

    public static class FilterBuilder {
        private static final FilterBuilder FILTERS = new FilterBuilder();
        public static FilterBuilder getFilterBuilder() { return FILTERS; }

        private final List<ModrinthCategories> categories = new ArrayList<>();
        private final List<String> versions = new ArrayList<>();
        private String license = null;
        private String clientSide = null;
        private String serverSide = null;

        public FilterBuilder addCategory(ModrinthCategories category) {
            categories.add(category);
            return this;
        }

        public FilterBuilder addCategories(ModrinthCategories... categories) {
            this.categories.addAll(Arrays.asList(categories));
            return this;
        }

        public FilterBuilder addVersion(String version) {
            versions.add(version);
            return this;
        }

        public FilterBuilder addVersions(String... versions) {
            this.versions.addAll(Arrays.asList(versions));
            return this;
        }

        public FilterBuilder setLicense(String licence) {
            this.license = license;
            return this;
        }

        public FilterBuilder setClientSided(String clientSided) {
            clientSide = clientSided;
            return this;
        }

        public FilterBuilder setServerSided(String serverSided) {
            serverSide = serverSided;
            return this;
        }

        public ModrinthFilter build() {
            return new ModrinthFilter(categories, versions, license, clientSide, serverSide);
        }

    }

}
