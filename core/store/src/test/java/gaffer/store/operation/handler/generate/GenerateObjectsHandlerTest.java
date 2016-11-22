/*
 * Copyright 2016 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gaffer.store.operation.handler.generate;

import gaffer.commonutil.iterable.CloseableIterable;
import gaffer.commonutil.iterable.CloseableIterator;
import gaffer.data.element.Element;
import gaffer.data.generator.ElementGenerator;
import gaffer.operation.OperationException;
import gaffer.operation.impl.generate.GenerateObjects;
import gaffer.store.Context;
import gaffer.store.Store;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GenerateObjectsHandlerTest {

    @Test
    public void shouldReturnObjects() throws OperationException {
        // Given
        final GenerateObjectsHandler<String> handler = new GenerateObjectsHandler<>();
        final Store store = mock(Store.class);
        final GenerateObjects<Element, String> operation = mock(GenerateObjects.class);
        final CloseableIterable<Element> elements = mock(CloseableIterable.class);
        final ElementGenerator<String> elementGenerator = mock(ElementGenerator.class);
        final CloseableIterable<String> objs = mock(CloseableIterable.class);
        final Context context = new Context();

        final CloseableIterator<String> objsIter = mock(CloseableIterator.class);
        given(objs.iterator()).willReturn(objsIter);
        given(elementGenerator.getObjects(elements)).willReturn(objs);
        given(operation.getElements()).willReturn(elements);
        given(operation.getElementGenerator()).willReturn(elementGenerator);

        // When
        final CloseableIterable<String> result = handler.doOperation(operation, context, store);

        // Then
        assertSame(objsIter, result.iterator());
    }
}