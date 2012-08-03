/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.oak.jcr;

import java.util.ArrayList;
import java.util.List;

import org.apache.jackrabbit.oak.plugins.lucene.LuceneEditor;
import org.apache.jackrabbit.oak.plugins.name.NameValidatorProvider;
import org.apache.jackrabbit.oak.plugins.name.NamespaceValidatorProvider;
import org.apache.jackrabbit.oak.plugins.type.DefaultTypeEditor;
import org.apache.jackrabbit.oak.plugins.type.TypeValidatorProvider;
import org.apache.jackrabbit.oak.plugins.value.ConflictValidatorProvider;
import org.apache.jackrabbit.oak.spi.commit.CommitEditor;
import org.apache.jackrabbit.oak.spi.commit.CompositeEditor;
import org.apache.jackrabbit.oak.spi.commit.CompositeValidatorProvider;
import org.apache.jackrabbit.oak.spi.commit.ValidatingEditor;
import org.apache.jackrabbit.oak.spi.commit.ValidatorProvider;

public class RepositoryTestUtils {

    public static CommitEditor buildDefaultCommitEditor() {
        List<CommitEditor> editors = new ArrayList<CommitEditor>();
        editors.add(new DefaultTypeEditor());
        editors.add(new ValidatingEditor(createDefaultValidatorProvider()));
        editors.add(new LuceneEditor());
        return new CompositeEditor(editors);
    }

    private static ValidatorProvider createDefaultValidatorProvider() {
        List<ValidatorProvider> providers = new ArrayList<ValidatorProvider>();
        providers.add(new NameValidatorProvider());
        providers.add(new NamespaceValidatorProvider());
        providers.add(new TypeValidatorProvider());
        providers.add(new ConflictValidatorProvider());
        return new CompositeValidatorProvider(providers);
    }

}
