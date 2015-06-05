/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * <p>
 * Please see distribution for license.
 */
package com.opengamma.strata.finance.credit.general.reference;

import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.finance.credit.common.RedCode;
import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Contains all the terms relevant to defining the reference entity and reference obligation(s)
 */
@BeanDefinition
public final class SingleNameReferenceInformation
    implements ReferenceInformation, ImmutableBean, Serializable {

  /**
   * The corporate or sovereign entity on which you are buying or selling protection and any
   * successor that assumes all or substantially all of its contractual and other obligations.
   * It is vital to use the correct legal name of the entity and to be careful not to choose a
   * subsidiary if you really want to trade protection on a parent company.
   * Please note, Reference Entities cannot be senior or subordinated.
   * It is the obligations of the Reference Entities that can be senior or subordinated.
   * ISDA 2003 Term: Reference Entity
   * <p>
   * The full company name of the entity
   * corresponding to the selected ticker is
   * displayed. Example: Ford Mtr Co.
   */
  @PropertyDefinition(validate = "notNull")
  final String referenceEntityName;

  /**
   * A legal entity identifier (e.g. RED entity code)
   */
  @PropertyDefinition(validate = "notNull")
  final RedCode referenceEntityId;

  /**
   * property of the reference obligation. Senior obligations will have a higher recovery rate
   * applied during pricing than subordinate securities.
   */
  @PropertyDefinition(validate = "notNull")
  final SeniorityLevel seniority;

  /**
   * currency property of the reference obligation
   */
  @PropertyDefinition(validate = "notNull")
  final Currency currency;

  /**
   * @return enum describe to distinguish that this is a single name CDS
   */
  @Override
  public ReferenceInformationType getType() {
    return ReferenceInformationType.SINGLE_NAME;
  }

  /**
   * The restructuring doc clause will be combined with this info to
   * form a key to find the correct associated curves for pricing
   * <p>
   * ShortName RedCode Tier Ccy
   * e.g. Agilent Tech Inc 008CA0 SNRFOR USD
   *
   * @return
   */
  @Override
  public String getMarketDataKeyName() {
    return String.format(
        "%s %s %s %s",
        getReferenceEntityName(),
        getReferenceEntityId(),
        getSeniority().getRedTierCode(),
        getCurrency()
    );
  }

  public static ReferenceInformation of(
      String referenceEntityName,
      RedCode referenceEntityId,
      SeniorityLevel seniority,
      Currency currency
  ) {
    return new SingleNameReferenceInformation(
        referenceEntityName,
        referenceEntityId,
        seniority,
        currency
    );
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SingleNameReferenceInformation}.
   * @return the meta-bean, not null
   */
  public static SingleNameReferenceInformation.Meta meta() {
    return SingleNameReferenceInformation.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SingleNameReferenceInformation.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static SingleNameReferenceInformation.Builder builder() {
    return new SingleNameReferenceInformation.Builder();
  }

  private SingleNameReferenceInformation(
      String referenceEntityName,
      RedCode referenceEntityId,
      SeniorityLevel seniority,
      Currency currency) {
    JodaBeanUtils.notNull(referenceEntityName, "referenceEntityName");
    JodaBeanUtils.notNull(referenceEntityId, "referenceEntityId");
    JodaBeanUtils.notNull(seniority, "seniority");
    JodaBeanUtils.notNull(currency, "currency");
    this.referenceEntityName = referenceEntityName;
    this.referenceEntityId = referenceEntityId;
    this.seniority = seniority;
    this.currency = currency;
  }

  @Override
  public SingleNameReferenceInformation.Meta metaBean() {
    return SingleNameReferenceInformation.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the corporate or sovereign entity on which you are buying or selling protection and any
   * successor that assumes all or substantially all of its contractual and other obligations.
   * It is vital to use the correct legal name of the entity and to be careful not to choose a
   * subsidiary if you really want to trade protection on a parent company.
   * Please note, Reference Entities cannot be senior or subordinated.
   * It is the obligations of the Reference Entities that can be senior or subordinated.
   * ISDA 2003 Term: Reference Entity
   * <p>
   * The full company name of the entity
   * corresponding to the selected ticker is
   * displayed. Example: Ford Mtr Co.
   * @return the value of the property, not null
   */
  public String getReferenceEntityName() {
    return referenceEntityName;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets a legal entity identifier (e.g. RED entity code)
   * @return the value of the property, not null
   */
  public RedCode getReferenceEntityId() {
    return referenceEntityId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets property of the reference obligation. Senior obligations will have a higher recovery rate
   * applied during pricing than subordinate securities.
   * @return the value of the property, not null
   */
  public SeniorityLevel getSeniority() {
    return seniority;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets currency property of the reference obligation
   * @return the value of the property, not null
   */
  public Currency getCurrency() {
    return currency;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SingleNameReferenceInformation other = (SingleNameReferenceInformation) obj;
      return JodaBeanUtils.equal(getReferenceEntityName(), other.getReferenceEntityName()) &&
          JodaBeanUtils.equal(getReferenceEntityId(), other.getReferenceEntityId()) &&
          JodaBeanUtils.equal(getSeniority(), other.getSeniority()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getReferenceEntityName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getReferenceEntityId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getSeniority());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("SingleNameReferenceInformation{");
    buf.append("referenceEntityName").append('=').append(getReferenceEntityName()).append(',').append(' ');
    buf.append("referenceEntityId").append('=').append(getReferenceEntityId()).append(',').append(' ');
    buf.append("seniority").append('=').append(getSeniority()).append(',').append(' ');
    buf.append("currency").append('=').append(JodaBeanUtils.toString(getCurrency()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SingleNameReferenceInformation}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code referenceEntityName} property.
     */
    private final MetaProperty<String> referenceEntityName = DirectMetaProperty.ofImmutable(
        this, "referenceEntityName", SingleNameReferenceInformation.class, String.class);
    /**
     * The meta-property for the {@code referenceEntityId} property.
     */
    private final MetaProperty<RedCode> referenceEntityId = DirectMetaProperty.ofImmutable(
        this, "referenceEntityId", SingleNameReferenceInformation.class, RedCode.class);
    /**
     * The meta-property for the {@code seniority} property.
     */
    private final MetaProperty<SeniorityLevel> seniority = DirectMetaProperty.ofImmutable(
        this, "seniority", SingleNameReferenceInformation.class, SeniorityLevel.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> currency = DirectMetaProperty.ofImmutable(
        this, "currency", SingleNameReferenceInformation.class, Currency.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "referenceEntityName",
        "referenceEntityId",
        "seniority",
        "currency");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1199381831:  // referenceEntityName
          return referenceEntityName;
        case -1949849399:  // referenceEntityId
          return referenceEntityId;
        case 184581246:  // seniority
          return seniority;
        case 575402001:  // currency
          return currency;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public SingleNameReferenceInformation.Builder builder() {
      return new SingleNameReferenceInformation.Builder();
    }

    @Override
    public Class<? extends SingleNameReferenceInformation> beanType() {
      return SingleNameReferenceInformation.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code referenceEntityName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> referenceEntityName() {
      return referenceEntityName;
    }

    /**
     * The meta-property for the {@code referenceEntityId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<RedCode> referenceEntityId() {
      return referenceEntityId;
    }

    /**
     * The meta-property for the {@code seniority} property.
     * @return the meta-property, not null
     */
    public MetaProperty<SeniorityLevel> seniority() {
      return seniority;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Currency> currency() {
      return currency;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1199381831:  // referenceEntityName
          return ((SingleNameReferenceInformation) bean).getReferenceEntityName();
        case -1949849399:  // referenceEntityId
          return ((SingleNameReferenceInformation) bean).getReferenceEntityId();
        case 184581246:  // seniority
          return ((SingleNameReferenceInformation) bean).getSeniority();
        case 575402001:  // currency
          return ((SingleNameReferenceInformation) bean).getCurrency();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code SingleNameReferenceInformation}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<SingleNameReferenceInformation> {

    private String referenceEntityName;
    private RedCode referenceEntityId;
    private SeniorityLevel seniority;
    private Currency currency;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(SingleNameReferenceInformation beanToCopy) {
      this.referenceEntityName = beanToCopy.getReferenceEntityName();
      this.referenceEntityId = beanToCopy.getReferenceEntityId();
      this.seniority = beanToCopy.getSeniority();
      this.currency = beanToCopy.getCurrency();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1199381831:  // referenceEntityName
          return referenceEntityName;
        case -1949849399:  // referenceEntityId
          return referenceEntityId;
        case 184581246:  // seniority
          return seniority;
        case 575402001:  // currency
          return currency;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1199381831:  // referenceEntityName
          this.referenceEntityName = (String) newValue;
          break;
        case -1949849399:  // referenceEntityId
          this.referenceEntityId = (RedCode) newValue;
          break;
        case 184581246:  // seniority
          this.seniority = (SeniorityLevel) newValue;
          break;
        case 575402001:  // currency
          this.currency = (Currency) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public SingleNameReferenceInformation build() {
      return new SingleNameReferenceInformation(
          referenceEntityName,
          referenceEntityId,
          seniority,
          currency);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code referenceEntityName} property in the builder.
     * @param referenceEntityName  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder referenceEntityName(String referenceEntityName) {
      JodaBeanUtils.notNull(referenceEntityName, "referenceEntityName");
      this.referenceEntityName = referenceEntityName;
      return this;
    }

    /**
     * Sets the {@code referenceEntityId} property in the builder.
     * @param referenceEntityId  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder referenceEntityId(RedCode referenceEntityId) {
      JodaBeanUtils.notNull(referenceEntityId, "referenceEntityId");
      this.referenceEntityId = referenceEntityId;
      return this;
    }

    /**
     * Sets the {@code seniority} property in the builder.
     * @param seniority  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder seniority(SeniorityLevel seniority) {
      JodaBeanUtils.notNull(seniority, "seniority");
      this.seniority = seniority;
      return this;
    }

    /**
     * Sets the {@code currency} property in the builder.
     * @param currency  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder currency(Currency currency) {
      JodaBeanUtils.notNull(currency, "currency");
      this.currency = currency;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("SingleNameReferenceInformation.Builder{");
      buf.append("referenceEntityName").append('=').append(JodaBeanUtils.toString(referenceEntityName)).append(',').append(' ');
      buf.append("referenceEntityId").append('=').append(JodaBeanUtils.toString(referenceEntityId)).append(',').append(' ');
      buf.append("seniority").append('=').append(JodaBeanUtils.toString(seniority)).append(',').append(' ');
      buf.append("currency").append('=').append(JodaBeanUtils.toString(currency));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}